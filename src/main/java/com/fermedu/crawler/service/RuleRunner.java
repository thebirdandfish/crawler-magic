package com.fermedu.crawler.service;

import com.fermedu.crawler.dao.RuleSpiderDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Program: book-crawler
 * @Create: 2020-12-31 18:06
 * @Author: JustThink
 * @Description: 被observer通知。查找rule与spider状态不一致的，运行或者停止爬虫
 * @Include:
 **/
@Service
@Slf4j
public class RuleRunner implements RuleSpiderObserver, Observer {

    @Autowired
    private SpiderRunner spiderRunnerImpl;

    /***
     * @Description manipulateSpiderByRule
     * @Params * @param listToWorkOn rule与spider状态不符的
     * @Return java.util.Map<java.lang.Long, com.fermedu.crawler.entity.Rule.Status>
     **/
    private void manipulateSpiderByRule(Map<String, RuleSpiderDao> mapToWorkOn) {

        /** condition rule on BUT spider is off or otherwise */
        mapToWorkOn.entrySet().stream().filter(e -> e.getValue().ifReadyToRunSpider())
                .forEach(e -> { // 需要打开对应spider
                    RuleSpiderDao toBeTurnedOn = e.getValue();
                    this.spiderRunnerImpl.turnOnLocalSpider(toBeTurnedOn.getDomain(), toBeTurnedOn.getSpiderStatus(), toBeTurnedOn.isReset());
                });

        /** condition rule off BUT spider is on or otherwise */
        mapToWorkOn.entrySet().stream().filter(e -> e.getValue().ifReadyToStopSpider())
                .forEach(e -> {
                    this.spiderRunnerImpl.turnOffLocalSpider(e.getValue().getDomain());
                });
    }

    /**
     * Switch通知，更新爬虫状态
     */
    @Override
    public void update(Observable o, Object arg) {
        Map<String, RuleSpiderDao> ruleSpiderDaoMap = (Map<String, RuleSpiderDao>) arg;

        log.info("{} has been notified with {}", this.getClass().getSimpleName(), ruleSpiderDaoMap);
        /** get the list, based on the list, sync rule and spider */
        this.manipulateSpiderByRule(ruleSpiderDaoMap);// getAndConsumeList() 获取到rule 和spider状态不一致的list
    }
}
