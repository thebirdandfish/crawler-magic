package com.fermedu.crawler.monitor;

import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-02-05 17:36
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderDenService {
    Map<String, CrawlerSpiderStatusMXBean> getAllSpiderStatuses();

    CrawlerSpiderStatusMXBean getSpiderStatusByDomain(String domain);
}

