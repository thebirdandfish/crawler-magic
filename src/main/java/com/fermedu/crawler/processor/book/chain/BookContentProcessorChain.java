package com.fermedu.crawler.processor.book.chain;

import com.alibaba.fastjson.JSON;
import com.fermedu.crawler.database.ReplacedDbService;
import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.book.BookContent;
import com.fermedu.crawler.entity.Replaced;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.ProcessorChainAbstract;
import com.guguskill.common.util.StringUtil;
import com.virjar.sipsoup.parse.XpathParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:30
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class BookContentProcessorChain extends ProcessorChainAbstract<BookRule> implements ProcessorChain<BookRule> {

    @Autowired
    private ReplacedDbService replacedDbService;

    private List<Replaced> getReplacedList() {
        return replacedDbService.findAll();
    }

    @Override
    protected boolean ifMatch(Page page) {
        /** verify */
        boolean matches = page.getRequest().getUrl().matches(rule.getContentUrlRegex());
        if (matches) {
            return true;
        }
        return false;
    }

    @Override
    protected void doOnMatch(Page page) {
        /** contentEntity */
        BookContent bookContent = new BookContent();
        /** preset */
        bookContent.setRuleId(rule.getId());

        /** extracted */
        // 1. contentUrl
        String contentUrl = page.getRequest().getUrl();
        bookContent.setContentUrl(contentUrl);
        // 2. contentChapterCode
        String contentChapterCode = StringUtil.retainByRegex(contentUrl, rule.getBookCodeInContentUrl());
        bookContent.setContentChapterCode(contentChapterCode);
        // 3. contentName
        String contentName = page.getHtml().xpath(rule.getContentPageContentName()).get();
        bookContent.setContentName(contentName);
        // 4. contentCode 如果单个章节页面分多页，每页的code。 可以为null
        String contentCode = StringUtil.retainByRegex(contentUrl, rule.getChapterCodeInContentUrl());
        bookContent.setContentCode(contentCode);
        // 5. contentText 正文
        List<String> contentList = XpathParser
                .compileNoError(rule.getContentPageContentEachText())
                .evaluateToString(Jsoup.parse(page.getHtml().get()));

        List<Replaced> regexList = this.getReplacedList();
        List<String> purgedList = ProcessorChain.purgeStringListWithRegexList(contentList, regexList);
        String contentText = JSON.toJSONString(purgedList);
        bookContent.setContentTextJsonArray(contentText);

        // putField
        page.putField(BookContent.class.getSimpleName(), Arrays.asList(bookContent));
    }
}
