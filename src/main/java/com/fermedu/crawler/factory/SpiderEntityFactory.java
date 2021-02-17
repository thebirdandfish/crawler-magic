package com.fermedu.crawler.factory;

import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 00:53
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderEntityFactory {
    SpiderEntity getSpiderEntityFrom(String domain, String spiderUuid, CrawlerSpiderStatusMXBean spiderStatus);

    static String combineUuidFrom(String domain, String ipAddress, Integer port) {
        String uuid = domain + "|" + ipAddress + ":" + port;
        return uuid;
    }
}
