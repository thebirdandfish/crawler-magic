package com.fermedu.crawler.database;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: book-crawler
 * @Create: 2021-02-09 17:41
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class RuleDbFacadeImplTest {

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;
    @Test
    void findByDomain() {
    }

    @Test
    void findAllId() {
    }

    @Test
    void findAllDomain() {
        List<String> allDomain = ruleDbFacadeImpl.findAllDomain();
        System.out.println(allDomain);
    }
}