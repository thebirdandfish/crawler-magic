package com.fermedu.crawler.service;

import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;

import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 15:51
 * @Author: JustThink
 * @Description: 其他类都通过本类查询spider的状态，系统中统一用spiderEntity而不是spiderStatus表示状态
 * @Include: SpiderDenService, SpiderDbService
 **/
public interface LocalSpiderQueryService {
    SpiderEntity findLocalSpiderEntityByDomain(String domain);

    Map<String, SpiderEntity> findLocalSpiderEntities();

    CrawlerSpiderStatusMXBean findLocalSpiderStatusByDomain(String domain);

    Map<String, CrawlerSpiderStatusMXBean> findLocalSpiderStatuses();

    SpiderEntity setLocalSpiderEntityOnInit(String domain);

    SpiderEntity setLocalSpiderEntityOnStop(String domain);
}
