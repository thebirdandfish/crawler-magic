package com.fermedu.crawler.processor.book.chain;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.book.BookChapter;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.ProcessorChainAbstract;
import com.guguskill.common.util.StringUtil;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:30
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class BookChapterListProcessorChain extends ProcessorChainAbstract<BookRule> implements ProcessorChain<BookRule> {

    @Override
    protected boolean ifMatch(Page page) {
        boolean matches = page.getRequest().getUrl().matches(rule.getChapterListUrlRegex());
        if (matches) {
            return true;
        }
        return false;
    }

    @Override
    protected void doOnMatch(Page page) {
        /** chapterEntity */

        List<BookChapter> bookChapterList = new ArrayList<>();
        // combine chapterNameList, chapterUrlList
        page.getHtml().xpath(rule.getChapterListPageChapterNodeXpath()).nodes()
                .stream().forEach(node -> {
            BookChapter bookChapter = new BookChapter();

            /** preset and causes */
            bookChapter.setRuleId(rule.getId());
            bookChapter.setChapterListUrl(page.getRequest().getUrl());

            /** extract */
            // 1. 2. chapterUrlList and chapterNameList
            String chapterUrl = node.links().regex(rule.getChapterUrlRegex()).get();
            bookChapter.setChapterUrl(chapterUrl);
            bookChapter.setChapterName(node.xpath(rule.getChapterListPageChapterNameInNode()).get());

            // 3 chapterCode
            String chapterCode = StringUtil.retainByRegex(chapterUrl, rule.getBookCodeInChapterUrl());
            bookChapter.setChapterCode(chapterCode);

            bookChapterList.add(bookChapter);
        });

        page.putField(BookChapter.class.getSimpleName(), bookChapterList);
    }
}
