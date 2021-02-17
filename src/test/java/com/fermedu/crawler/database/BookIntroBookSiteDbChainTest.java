package com.fermedu.crawler.database;

import com.fermedu.crawler.pipeline.book.chain.BookSiteDbChain;
import com.fermedu.crawler.entity.book.BookSite;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-16 02:56
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
class BookIntroBookSiteDbChainTest {

    @Autowired
    private BookSiteDbChain bookSiteDbChain;


    @Test
    void findOne() {

        BookSite bookSite = new BookSite();

        bookSite.setId(new Long(2));

        BookSite bookSite1 = bookSiteDbChain.findOne(bookSite);

        System.out.println(bookSite1);
    }

    @Test
    void addOrUpdateList() {
        BookSite bookSite1 = new BookSite();
        BookSite bookSite2 = new BookSite();
        BookSite bookSite3 = new BookSite();

        bookSite1.setIndexSiteTitle("i wanna update ruleId 4");
        bookSite1.setRuleId(new Long(4));
        bookSite1.setBookUrlListJson(null);

        bookSite2.setIndexSiteTitle("i wanna add another under ruleId 2");
        bookSite2.setRuleId(new Long(2));
        bookSite2.setId(new Long(2));
        bookSite2.setBookUrlListJson(null);

        List<BookSite> list = Arrays.asList(bookSite1, bookSite2);

        List<BookSite> list1 = bookSiteDbChain.addOrUpdateList(list);

        System.out.println(list1);
    }

    @Test
    void addOrUpdate() {
        BookSite bookSite = new BookSite();

        bookSite.setIndexSiteTitle("fewfew5");
        bookSite.setRuleId(new Long(4));
        bookSite.setBookUrlListJson("[test5]");

        BookSite bookSite1 = bookSiteDbChain.addOrUpdateOne(bookSite);

        System.out.println(bookSite1);
    }

    @Test
    void find() {
        BookSite bookSite = new BookSite();

        bookSite.setId(new Long(4));

        BookSite bookSite1 = bookSiteDbChain.findOne(bookSite);

        System.out.println(bookSite1);
    }

    @Test
    void findList() {
        BookSite bookSite = new BookSite();
        bookSite.setId(new Long(1));

        List<BookSite> list1 = bookSiteDbChain.findList(bookSite);

        System.out.println(list1);
    }
}