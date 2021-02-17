package com.fermedu.crawler.entity;

import com.fermedu.crawler.pipeline.book.chain.BookSiteDbChain;
import com.fermedu.crawler.entity.book.BookSite;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 17:45
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookIntroSiteTest {

    @Autowired
    private BookSiteDbChain bookSiteDbChain;

    @Test
    void getBookUrlStringList() {
        BookSite bookSite = new BookSite();
        bookSite.setRuleId(new Long(1));

        BookSite bookSite1 = bookSiteDbChain.findOne(bookSite);

        bookSite1.getBookUrlList().stream().forEach(e -> {

            System.out.println(e);
                });


    }

}