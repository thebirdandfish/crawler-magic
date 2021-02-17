package com.fermedu.crawler.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-31 22:05
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookIntroRuleRunnerTest {

    @Autowired
    private RuleRunner ruleRunner;

    @Test
    void refresh() {
//        ruleSpiderObserver.refresh();
    }
}