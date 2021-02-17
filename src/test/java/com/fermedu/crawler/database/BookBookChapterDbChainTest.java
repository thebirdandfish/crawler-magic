package com.fermedu.crawler.database;

import com.fermedu.crawler.pipeline.book.chain.BookChapterDbChain;
import com.fermedu.crawler.entity.book.BookChapter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-30 14:05
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class BookBookChapterDbChainTest {

    @Autowired
    private BookChapterDbChain bookChapterDbChain;

    @Test
    void addOrUpdateList() {
        BookChapter example1 = new BookChapter();
        BookChapter example2 = new BookChapter();
        example1.setChapterName("test name1");
        example2.setChapterName("test name2");
        example1.setChapterUrl("http://m.31xs.com/139/139967/5735525.html");
        example2.setChapterUrl("http://m.31xs.com/139/139967/5735525.html");

        List<BookChapter> bookChapterList = bookChapterDbChain.addOrUpdateList(Arrays.asList(example1, example2));
        System.out.println(bookChapterList);
    }

    @Test
    void addOrUpdateOne() {
        BookChapter example = new BookChapter();
        example.setChapterName("test name2");
        example.setChapterUrl("http://m.31xs.com/139/139967/5735525.html");

        BookChapter bookChapter = bookChapterDbChain.addOrUpdateOne(example);
        System.out.println(bookChapter);
    }

    @Test
    void findOne() {
        BookChapter example = new BookChapter();
        example.setId(new Long(56));
        example.setChapterUrl("http://m.31xs.com/139/139967/57355259.html");

        BookChapter bookChapter = bookChapterDbChain.findOne(example);
        System.out.println(bookChapter);
    }

    @Test
    void findList() {
    }
}