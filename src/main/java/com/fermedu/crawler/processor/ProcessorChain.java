package com.fermedu.crawler.processor;

import com.fermedu.crawler.entity.Replaced;
import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.RuleGeneric;
import com.guguskill.common.util.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface ProcessorChain<T extends RuleGeneric> extends PageProcessor {
    ProcessorChain initRule(T rule);

    ProcessorChain initNext(ProcessorChain nextChain);

    @Override
    void process(Page page);

    @Override
    Site getSite();

    /***
     * @Description purgeStringListWithRegexList purge the rawList with regexList
     * @Params * @param rawList
     * @param replacedList
     * @Return java.util.List<java.lang.String>
     **/
    static List<String> purgeStringListWithRegexList(List<String> rawList, List<Replaced> replacedList) {
        List<String> purgedList = rawList.stream()
                .filter(eachContent -> !StringUtil.isBlank(eachContent))
                .map(eachContent -> {
                    /** use AtomicReference to purge each content */
                    AtomicReference<String> contentRef = new AtomicReference<>();
                    contentRef.set(eachContent);
                    replacedList.stream().forEach(replaced -> {
                        contentRef.set(StringUtil.replaceRegexWith(contentRef.get(), replaced.getFromRegex(), replaced.getIntoString()));
                    });

                    return contentRef.get();
                }).collect(Collectors.toList());

        return purgedList;
    }

    /** 从page中提取所有匹配的url链接 */
    static List<String> getAllPotentialUrl(Selectable links, List<String> allUrlRegex) {
        List<String> allPotentialUrl = new ArrayList<>();

        allUrlRegex.stream().forEach(urlRegex->{
            allPotentialUrl.addAll(links.regex(urlRegex).all());
        });

        return allPotentialUrl;
    }
}
