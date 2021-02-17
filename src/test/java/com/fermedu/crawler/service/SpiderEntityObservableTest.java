package com.fermedu.crawler.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2021-01-01 01:02
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class SpiderEntityObservableTest {

    @Autowired
    private RuleSpiderUpdater ruleSpiderUpdater;

    @Test
    void notifyObservers() {
        RuleSpiderUpdater ruleSpiderUpdater = new RuleSpiderUpdater();

        ruleSpiderUpdater.notifyObservers();
    }

    @Test
    void getAllSwitchControl() {
//        List<SpiderEntity> allSpiderEntity = switchObservable.getAllSwitchControl();
//        System.out.println(allSpiderEntity);
    }

    @Test
    void fromSpiderStatus() {



    }

    @Test
    void ifRuleSpiderConsistent() {

    }
}