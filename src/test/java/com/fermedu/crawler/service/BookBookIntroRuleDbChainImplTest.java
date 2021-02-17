package com.fermedu.crawler.service;

import com.fermedu.crawler.database.BookRuleDbChain;
import com.fermedu.crawler.entity.BookRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-14 23:11
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookBookIntroRuleDbChainImplTest {

    @Autowired
    private BookRuleDbChain bookRuleDbServiceImpl;

    @Test
    public void addOrUpdateRule() {
        BookRule bookRule = new BookRule();
        bookRule.setBookSourceName("31小说网");
        bookRule.setDomain("m.31xs.com");
        bookRule.setBookSourceBaseUrlRegex("https?://\\w{0,10}.?31xs.com/");

        bookRule.setIndexUrlRegex("https?://\\w{0,10}.?31xs.(com/?)$");

        bookRule.setCateUrlRegex("https?://\\w{0,10}.?31xs.com/list/\\d+/");
        bookRule.setCatePageCateNodeXpath("//a[@href*=\"list\"]");
        bookRule.setCatePageCateNameInNode("/*/text()");
        bookRule.setCateCodeInCateUrl("(?<=\\/list/)\\d+(?=)");

        bookRule.setBookUrlRegex("https?://\\w{0,10}.?31xs.com/\\d+/\\d+/");
        bookRule.setBookPageCateNodeXpath("//ol/li/a[@href*=\"list\"]");
        bookRule.setBookPageCateNameInNode("/*/text()");
        bookRule.setBookPageBookStatus("//meta[@property*=\"status\"]/@content");
        bookRule.setBookPageBookCover(null);
        bookRule.setBookPageBookName("//h1[@class*=\"article-title\"]/text()");
        bookRule.setBookPageBookAuthor("//meta[@property*=\"author\"]/@content");
        bookRule.setBookPageBookIntro("//div[@class*=\"article-lead\"]/text()");
        bookRule.setBookPageBookLatestChapter("//dl/dd[1]/a/text()");

        bookRule.setChapterListUrlRegex("https?://\\w{0,10}.?31xs.com/\\d+/\\d+/index.htm");
        bookRule.setChapterUrlRegex("https?://\\w{0,10}.?31xs.com/\\d+/\\d+/\\d+.html");
        bookRule.setChapterListPageChapterNodeXpath("//ul/li/a");
        bookRule.setBookCodeInChapterUrl("(?<=[a-z]{1,30}\\/\\d{1,30}/)\\d+(?=)");

        bookRule.setContentUrlRegex("https?://\\w{0,10}.?31xs.com/\\d+/\\d+/\\d+.html");
        bookRule.setContentPageContentName("//div[@class*=\"article-hd\"]/h3/text()");
        bookRule.setBookCodeInContentUrl("(?<=/\\d{1,30}/)\\d+(?=/\\d{1,30}.html)");
        bookRule.setChapterCodeInContentUrl("(?<=\\/\\d{1,30}\\/\\d{1,30}\\/)\\d{1,30}(?=.html)");
        bookRule.setContentPageContentEachText("//div[contains(@class,\"article-bd\")]/p/text()");

        bookRuleDbServiceImpl.addOrUpdateOne(bookRule);
    }

    @Test
    void find() {
        BookRule bookRule = new BookRule();
        bookRule.setBookSourceName("31小说网");
        bookRule.setId(new Long(111));

        System.out.println(bookRuleDbServiceImpl.findList(bookRule));
    }
}