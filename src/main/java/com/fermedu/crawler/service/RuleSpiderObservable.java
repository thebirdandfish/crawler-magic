package com.fermedu.crawler.service;

import com.fermedu.crawler.dao.RuleSpiderDao;

import java.util.List;
import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-01-01 00:54
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface RuleSpiderObservable {
    /** 取得本机的ruleSpiderDao */
    Map<String, RuleSpiderDao> getLocalRuleSpiderDao(); // (domain, ruleSpiderDao)
}
