package com.fermedu.crawler.processor.book.chain;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.book.BookIntro;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.ProcessorChainAbstract;
import com.fermedu.crawler.util.UrlFastUtil;
import com.guguskill.common.util.StringUtil;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Arrays;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:29
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class BookIntroProcessorChain extends ProcessorChainAbstract<BookRule> implements ProcessorChain<BookRule> {

    @Override
    protected boolean ifMatch(Page page) {
        boolean matches = page.getRequest().getUrl().matches(rule.getBookUrlRegex());
        if (matches) {
            return true;
        }
        return false;
    }

    @Override
    protected void doOnMatch(Page page) {
        /** bookEntity */
        BookIntro book = new BookIntro();

        /** preset and causes */
        book.setBookUrl(page.getRequest().getUrl());
        book.setRuleId(rule.getId());

        /** extract , putField*/
        // 1 cateNode
        Selectable cateNode = page.getHtml().xpath(rule.getBookPageCateNodeXpath() );
        // 1.1 cateUrl
        String cateUrl = cateNode.links().regex(rule.getCateUrlRegex()).get();
        String completeCateUrl = UrlFastUtil.complete(page.getRequest().getUrl(), cateUrl);
        book.setCateUrl(completeCateUrl);
        // 1.2 cateCode
        String cateCode = StringUtil.retainByRegex(cateUrl, rule.getCateCodeInCateUrl()); // 从cateUrl中提取，保留正则匹配的部分
        book.setCateCode(cateCode);
        // 1.3 cateName
        String cateName = cateNode.xpath(rule.getBookPageCateNameInNode()).get();
        book.setCateName(cateName);
        // 4 bookAuthor
        String bookAuthor = page.getHtml().xpath(rule.getBookPageBookAuthor()).get();
        book.setBookAuthor(bookAuthor);
        // 5 bookIntro
        String bookIntro = page.getHtml().xpath(rule.getBookPageBookIntro()).get();
        book.setBookIntro(bookIntro);
        // 6 bookName
        String bookName = page.getHtml().xpath(rule.getBookPageBookName()).get();
        book.setBookName(bookName);
        // 7 bookCover url
        if (rule.getBookPageBookCover() == null) {
        } else {
            String bookCover = page.getHtml().xpath(rule.getBookPageBookCover()).get();
            book.setBookCover(bookCover);
        }
        // 8 bookStatus
        String bookStatus = page.getHtml().xpath(rule.getBookPageBookStatus()).get();
        book.setBookStatus(bookStatus);
        // 7 bookLatestChapter
        String bookLatestChapter = page.getHtml().xpath(rule.getBookPageBookLatestChapter()).get();
        book.setBookLatestChapter(bookLatestChapter);
        // 8 bookChapterListUrl
        String bookChapterListUrl = page.getHtml().links().regex(rule.getChapterListUrlRegex()).get();
        book.setBookChapterListUrl(bookChapterListUrl);

        /** putField */
        page.putField(BookIntro.class.getSimpleName(), Arrays.asList(book));
    }
}
