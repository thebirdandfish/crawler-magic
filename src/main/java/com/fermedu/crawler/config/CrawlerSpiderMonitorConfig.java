package com.fermedu.crawler.config;

import com.fermedu.crawler.monitor.CrawlerSpiderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @Program: book-crawler
 * @Create: 2021-01-06 20:08
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Configuration
@Slf4j
public class CrawlerSpiderMonitorConfig {

    @Bean
    public SpiderMonitor spiderMonitor() {
        SpiderMonitor spiderMonitor = new SpiderMonitor() {
            @Override
            protected SpiderStatusMXBean getSpiderStatusMBean(Spider spider, MonitorSpiderListener monitorSpiderListener) {
                return new CrawlerSpiderStatus(spider, monitorSpiderListener);
            }
        };

        return spiderMonitor;
    }
}
