package com.fermedu.crawler.processor;

import com.fermedu.crawler.config.ClassificationConfig;
import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.RuleGeneric;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-13 04:22
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Slf4j
public abstract class ProcessorChainAbstract<T extends RuleGeneric> implements ProcessorChain<T> {

    protected ProcessorChain next;

    protected T rule;

    private void logMismatch(String currentUrl) {
        String nextString;
        if (next == null) {
            nextString = "";
        } else {
            nextString = ". calling next "
                    + next.getClass().getSimpleName();
        }


        log.trace(currentUrl
                + " does not match the "
                + this.getClass().getSimpleName()
                + nextString);
    }

    protected abstract boolean ifMatch(Page page); // 覆写页面是否匹配

    protected abstract void doOnMatch(Page page); // 确认匹配后的执行逻辑

    @Override
    public ProcessorChain initRule(T rule) {
        this.rule = rule;
        return this;
    }

    @Override
    public ProcessorChain initNext(ProcessorChain nextChain) {
        this.next = nextChain;
        return this;
    }

    @Override
    public void process(Page page) {
        if (ifMatch(page)) {
            this.doOnMatch(page);
        } else {
            this.logMismatch(page.getRequest().getUrl());
        }

        /** next chain in the chains */
        if (this.next != null) {
            next.process(page);
        } else { // 当前是最后一环
            List<String> allPotentialUrl = ProcessorChain.getAllPotentialUrl(page.getHtml().links(), rule.getAllUrlRegex());
            page.addTargetRequests(allPotentialUrl); // 添加后续任务
        }
    }

    @Override
    public Site getSite() {
        return ClassificationConfig.get(rule.getClass()).getSite(rule);
    }
}
