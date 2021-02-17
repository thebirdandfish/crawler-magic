package com.fermedu.crawler.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2021-01-23 16:49
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookIntroRuleSpiderUpdaterTest {

    @Autowired
    private RuleSpiderUpdater ruleSpiderUpdater;

    @Test
    void updateRuleAndSpiderStatuesInDb() {
//        List<RuleSpiderDao> ruleSpiderDaos = switchObservable.updateRuleAndSpiderStatuesInDb();
//        System.out.println(ruleSpiderDaos);
    }
}