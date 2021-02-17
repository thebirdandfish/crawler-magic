package com.fermedu.crawler.monitor;

import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import com.guguskill.common.util.SimHashUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatus;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @Program: book-crawler
 * @Create: 2020-12-15 14:10
 * @Author: JustThink
 * @Description: 每只爬虫的状态对象 SpiderStatusMXBean。单独对应一只爬虫，描述每只爬虫的单独的状态。
 * @Include:
 * @Demo: https://github.com/code4craft/webmagic/blob/master/webmagic-extension/src/test/java/us/codecraft/webmagic/monitor/CustomSpiderStatus.java
 **/
@Slf4j
public class CrawlerSpiderStatus extends SpiderStatus implements SpiderStatusMXBean, CrawlerSpiderStatusMXBean {

    protected final Spider spider;

    protected final SpiderMonitor.MonitorSpiderListener monitorSpiderListener;

    public CrawlerSpiderStatus(Spider spider, SpiderMonitor.MonitorSpiderListener monitorSpiderListener) {
        super(spider, monitorSpiderListener);
        this.spider = spider;
        this.monitorSpiderListener = monitorSpiderListener;
    }

    /***
    * @Description getStatusEnum 为null时返回Stopped.增量改动
    * @Params * @param 
    * @Return com.fermedu.crawler.enumeration.SpiderStatusEnum
    **/
    @Override
    public SpiderStatusEnum getStatusEnum() {
        return SpiderStatusEnum.fromWebmagicSpiderStatus(spider.getStatus());
    }

    @Override
    public void startSpiderByRunAsync() {
        super.start(); // 启动spider，也就是runAsync
    }

    @Override
    public void stopSpiderAndDestroy() {
        super.stop(); // 关闭爬虫， 也就是destroy
    }

    @Override
    public Long getRuleId() {
        return SimHashUtil.simHash(this.getDomain());
    }

    @Override
    public String getDomain() {
        return spider.getSite().getDomain();
    }

    @Override
    public String getSpiderUuid() {
        return spider.getUUID(); // 是创建spider的时候设置的
    }
}
