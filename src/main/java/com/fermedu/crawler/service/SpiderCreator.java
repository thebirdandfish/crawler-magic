package com.fermedu.crawler.service;

import com.fermedu.crawler.entity.RuleGeneric;
import us.codecraft.webmagic.Spider;

/**
 * @Program: book-crawler
 * @Create: 2021-02-08 16:42
 * @Author: JustThink
 * @Description: 按照每个rule类别，例如book，创建对应的spiderCreator
 * @Include:
 **/
public interface SpiderCreator {
    Spider createSpider(RuleGeneric ruleGeneric, boolean reset);
}
