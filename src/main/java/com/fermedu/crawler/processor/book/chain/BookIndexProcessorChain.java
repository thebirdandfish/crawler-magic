package com.fermedu.crawler.processor.book.chain;

import com.alibaba.fastjson.JSONObject;
import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.book.BookSite;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.ProcessorChainAbstract;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:28
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class BookIndexProcessorChain extends ProcessorChainAbstract<BookRule> implements ProcessorChain<BookRule> {

    @Override
    protected boolean ifMatch(Page page) {
        /** verify */
        boolean matches = page.getRequest().getUrl().matches(rule.getIndexUrlRegex());
        if (matches) {
            return true;
        }
        return false;
    }

    @Override
    protected void doOnMatch(Page page) {
        /** siteEntity */
        BookSite bookSite = new BookSite();

        /** preset and causes */
        bookSite.setRuleId(rule.getId());

        /** extract , putField*/
        /** 1 site */
        String siteTitle = page.getHtml().xpath(rule.getIndexPageSiteTitle()).get();
        bookSite.setIndexSiteTitle(siteTitle);
        List<String> bookUrlList = page.getHtml().links().regex(rule.getBookUrlRegex()).all();/** links().regex() 即便抓到残缺连接/14/14153 ，webmagic会自动补全成 http://m.31xs.com/14/14153 */

        bookSite.setBookUrlListJson(JSONObject.toJSONString(bookUrlList));

        List<BookSite> bookSiteList = Stream.of(bookSite).collect(Collectors.toList());

        page.putField(BookSite.class.getSimpleName(), bookSiteList);
    }
}
