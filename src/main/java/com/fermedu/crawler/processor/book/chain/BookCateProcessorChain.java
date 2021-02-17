package com.fermedu.crawler.processor.book.chain;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.book.BookCate;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.ProcessorChainAbstract;
import com.guguskill.common.util.StringUtil;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:28
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class BookCateProcessorChain extends ProcessorChainAbstract<BookRule> implements ProcessorChain<BookRule> {

    @Override
    protected boolean ifMatch(Page page) {
        boolean matches = page.getRequest().getUrl().matches(rule.getCateUrlRegex());
        if (matches) {
            return true;
        }
        return false;
    }

    @Override
    protected void doOnMatch(Page page) {
        List<BookCate> bookCateList = new ArrayList<>();

        page.getHtml().xpath(rule.getCatePageCateNodeXpath()).nodes()
                .stream().forEach(node ->
        {
            /** cateEntity */
            BookCate bookCate = new BookCate();

            /** preset and causes */
            bookCate.setRuleId(rule.getId());
            /** extract , putField*/
            // 1. cateUrl
            String cateUrl = node.links().regex(rule.getCateUrlRegex()).get();
            bookCate.setCateUrl(cateUrl);
            // 2 cateCode
            String cateCode = StringUtil.retainByRegex(cateUrl, rule.getCateCodeInCateUrl());
            bookCate.setCateCode(cateCode);
            // 3 cateName
            String cateName = node.xpath(rule.getCatePageCateNameInNode()).get();
            bookCate.setCateName(cateName);

            bookCateList.add(bookCate);
        });
        page.putField(BookCate.class.getSimpleName(), bookCateList);
    }
}
