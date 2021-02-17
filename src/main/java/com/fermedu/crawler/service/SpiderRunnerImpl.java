package com.fermedu.crawler.service;

import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.database.RuleDbFacade;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;

import java.util.Date;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 14:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SpiderRunnerImpl implements SpiderRunner {

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;

    @Autowired
    private SpiderCreator spiderCreatorImpl;

    @Autowired
    private LocalSpiderQueryService localSpiderQueryServiceImpl;

    /**
     * 1通过domain找到对应localSpiderStatus， 2运行
     */
    private void runSpiderByDomain(String domain) {
        CrawlerSpiderStatusMXBean spiderStatusByDomain = localSpiderQueryServiceImpl.findLocalSpiderStatusByDomain(domain);
        if (spiderStatusByDomain == null) {
            String msg = domain + "找不到对应的spiderStatus";
            log.info(msg);
            throw new GuInternalException(ResultEnum.VALUE_NULL.getCode(), msg);
        }
        log.info(new Date() + " 找到了对应爬虫，直接运行爬虫");

        spiderStatusByDomain.startSpiderByRunAsync();
    }

    /**
     * 通过domain找rule， 通过rule创建爬虫。
     * 爬虫的实例不需要返回。runner会从monitor里面找
     */
    private Spider createSpider(String domain, boolean reset) {
        // 找到rule，启动爬虫
        RuleGeneric rule = ruleDbFacadeImpl.findByDomain(domain); // find rule
        Spider spider = spiderCreatorImpl.createSpider(rule, reset);// create and run spider by the rule

        log.info(new Date() + " 爬虫被创建\n"
                + "SpiderUUID: " + spider.getUUID() + "; Domain: " + domain);
        return spider;
    }


    @Async
    @Transactional
    @Override
    public void turnOnLocalSpider(String domain, SpiderStatusEnum spiderStatus, boolean reset) {
        // 更新spiderDbService
        localSpiderQueryServiceImpl.setLocalSpiderEntityOnInit(domain);

        if (spiderStatus.equals(SpiderStatusEnum.UNCREATED)) { // 2. 需要先create
            log.info(new Date() + " 爬虫没有被创建，准备创建爬虫");

            Spider spider = this.createSpider(domain, reset);
        } else { // 1.已经有spider，直接运行
            log.info(new Date() + " 爬虫已经被创建，准备查找对应SpiderStatus");
        }
        this.runSpiderByDomain(domain);
    }

    @Async
    @Transactional
    @Override
    public void turnOffLocalSpider(String domain) {
        // 更新spiderDbservice
        localSpiderQueryServiceImpl.setLocalSpiderEntityOnStop(domain);

        // 停止爬虫
        localSpiderQueryServiceImpl.findLocalSpiderStatusByDomain(domain)
                .stopSpiderAndDestroy(); // stop the spider
    }

}
