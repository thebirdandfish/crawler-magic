package com.fermedu.crawler.monitor;

import com.fermedu.crawler.util.UrlFastUtil;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 01:18
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SpiderCommonDecoratorImpl implements SpiderCommonDecorator {

    @Autowired
    private SpiderMonitor spiderMonitor;

    @Autowired
    private RedisScheduler redisScheduler;

    /**
     * 是：queue空但是set有值
     */
    private static boolean queueEmptySetFull(RedisScheduler redisScheduler, Task spider) {
        int queue = redisScheduler.getLeftRequestsCount(spider);
        if (queue > 0) { // queue not empty
            return false;
        } // queue empty

        int set = redisScheduler.getTotalRequestsCount(spider);
        if (set > 0) { // set full
            return true;
        }// set empty

        return false;
    }

    private void addProxy(Spider spider) {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        List<Proxy> proxyList = new ArrayList<>();
        // future
        proxyList.add(new Proxy("101.101.101.101", 8888, "", ""));
        Proxy[] proxies = proxyList.toArray(new Proxy[proxyList.size()]);
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(proxies));

        spider.setDownloader(httpClientDownloader);
    }

    private void duplicationRemoval(Spider spider) {
        spider.setScheduler(new QueueScheduler()
                .setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)) //1000000是估计的页面数量
        );
    }

    /***
     * @Description fileCacheQueueScheduler 与其他scheduler互斥。单机（非集群）可以不用redis，用本地的file
     * @Params * @param spider
     * @Return void
     **/
    private void fileCacheQueueScheduler(Spider spider) {
        spider.setScheduler(new FileCacheQueueScheduler("/data/FileCacheQueue"));
    }

    /***
     * @Description redisScheduler 适用于单机/集群。redis会保存queue以及已爬取的url
     * @Params * @param spider
     * @Return void
     **/
    private void redisScheduler(Spider spider, boolean reset) {
        /** 新建redisScheduler */
        if (queueEmptySetFull(redisScheduler, spider) || reset == true) {
            /** 如果set有值但是queue没有值，说明上次爬虫已经运行完毕
             * 本次create爬虫，必须先清空set */
            redisScheduler.resetDuplicateCheck(spider);
        }

        spider.setScheduler(redisScheduler);
    }

    /***
     * @Description monitor 通过monitor实现监视，操作spider，例如start stop等
     * @Params * @param spider
     * @Return void
     **/
    private void register(Spider spider) {
        try {
            this.spiderMonitor.register(spider);
        } catch (Exception e) {
            String eMsg = ResultEnum.JM_EXCEPTION_REGISTER_FAILED.getMessage() + "\n"
                    + "JMException SpiderMonitor.instance().register(spider) failed\n"
                    + "spider: " + spider
                    + "Cause: " + e.getCause();
            log.error(eMsg);
            throw new GuInternalException(ResultEnum.NEW_INSTANCE_FAILED.getCode(), eMsg);
        }
    }

    /**
     * 通过以上所有方法，装饰spider
     */
    @Transactional
    @Override
    public void decorate(Spider spider, boolean reset) {
        this.register(spider); // 注册到FermSpiderMonitor
        this.duplicationRemoval(spider); // 去重复
        this.redisScheduler(spider, reset); // 添加redis集群配置 （非本地文件方式）
        /** spider thread */
        spider.thread(5);

        /** starting request (do this after the scheduler) */
        String domain = spider.getSite().getDomain();
        String indexUrl = UrlFastUtil.setTimestampParamToGetUrl(domain);
        spider.addUrl(indexUrl);
    }
}
