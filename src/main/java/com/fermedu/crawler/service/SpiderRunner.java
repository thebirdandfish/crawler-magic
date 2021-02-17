package com.fermedu.crawler.service;

import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;

/**
 * @Program: book-crawler
 * @Create: 2021-01-26 00:26
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderRunner {
    void turnOffLocalSpider(String domain);

    void turnOnLocalSpider(String domain, SpiderStatusEnum spiderStatus, boolean reset);
}
