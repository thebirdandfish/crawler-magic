package com.fermedu.crawler.service;

import com.fermedu.crawler.database.BookRuleDbChain;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 15:12
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
class SpiderRunnerImplTest {

    @Autowired
    private BookRuleDbChain bookRuleDbServiceImpl;

    @Autowired
    private SpiderRunnerImpl aSpiderRunnerImpl;

    @Test
    void turnOnRule() {
//        Rule rule = new Rule();
//        rule.setBookSourceName("31小说网");
//        Rule rule1 = ruleDbService.findOne(rule);
//        aRuleRunner.createSpiderByRule(rule1);

    }
}