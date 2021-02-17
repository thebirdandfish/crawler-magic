package com.fermedu.crawler.monitor;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @Program: book-crawler
 * @Create: 2021-01-07 01:22
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
class SpiderCommonDecoratorImplTest {

    @Test
    public void name() {
        SpiderMonitor spiderMonitor = new SpiderMonitor() {
            @Override
            protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
                return new CrawlerSpiderStatus(spider, monitorSpiderListener);
            }
        };

//        Spider spider = Spider
//                .create(new BookPageProcessorImpl(new Rule()))
//                .addUrl("http://www.qq.com")
//                ;
//
//        try {
//            spiderMonitor.register(spider);
//        } catch (JMException e) {
//            e.printStackTrace();
//        }
    }
}