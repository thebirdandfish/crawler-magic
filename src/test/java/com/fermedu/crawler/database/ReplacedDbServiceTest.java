package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.Replaced;
import com.fermedu.crawler.repository.ReplacedRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Program: book-crawler
 * @Create: 2020-12-18 20:42
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class ReplacedDbServiceTest {
    @Autowired
    private ReplacedDbService replacedDbService;

    @Autowired
    private ReplacedRepository replacedRepository;

@Test
    public void insert() {
        Replaced replaced = new Replaced();

//        replaced.setFromRegex("\\u63d2\\u4e00\\u53e5\\uff0c.{10,500}\\u90fd\\u652f\\u6301\\uff01");

        replaced.setFromRegex("\\u54aa\\u54aa\\u9605\\u8bfb.{3,500}\\u002e\\u002e\\u002e");

        replacedRepository.save(replaced);
    }
}