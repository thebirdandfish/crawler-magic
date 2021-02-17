package com.fermedu.crawler.config;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.pipeline.PipelineChain;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Site;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: book-crawler
 * @Create: 2021-02-09 01:15
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookClassificationConfigTest {

    @Test
    void ifSameClassification() {

        BookClassificationConfig bookClassificationConfig = new BookClassificationConfig();
        boolean result = bookClassificationConfig.ifSameClassification(BookRule.class);

        System.out.println(result);
    }

    @Test
    void get() {
        ClassificationConfig bookConfig = ClassificationConfig.get(BookRule.class);
        BookRule bookRule = new BookRule();
        bookRule.setDomain("www.111.com");
        Site site = bookConfig.getSite(bookRule);
        System.out.println(site.getRetryTimes());

        PipelineChain pipelineDbChain = bookConfig.getPipelineDbChain();
        System.out.println(pipelineDbChain);
    }
}
