package com.fermedu.crawler.config;

import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.chain.*;
import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.processor.ProcessorChain;
import com.fermedu.crawler.processor.book.chain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Site;

/**
 * @Program: book-crawler
 * @Create: 2021-02-07 13:10
 * @Author: JustThink
 * @Description: Book类目下的配置.编排db和processor的链式逻辑
 * @Include:
 **/
@Configuration
@Slf4j
public class BookClassificationConfig extends ClassificationAbstract<BookRule> implements ClassificationConfig<BookRule> {

    @Override
    protected Class<BookRule> ruleClz() {
        return BookRule.class;
    }

    @Autowired
    private BookSiteDbChain bookSiteDbChain;

    @Autowired
    private BookCateDbChain bookCateDbChain;

    @Autowired
    private BookIntroDbChain bookIntroDbChain;

    @Autowired
    private BookChapterDbChain bookChapterDbChain;

    @Autowired
    private BookContentDbChain bookContentDbChain;

    /**
     * 获取db链式处理器
     */
    public PipelineChain getPipelineDbChain() {
        PipelineChainAbstract chain1 = bookSiteDbChain.initNext(null);
        PipelineChainAbstract chain2 = bookCateDbChain.initNext(chain1);
        PipelineChainAbstract chain3 = bookIntroDbChain.initNext(chain2);
        PipelineChainAbstract chain4 = bookChapterDbChain.initNext(chain3);
        PipelineChainAbstract chain5 = bookContentDbChain.initNext(chain4);

        return chain5;
    }

    @Autowired
    private BookIndexProcessorChain bookIndexProcessorChain;

    @Autowired
    private BookCateProcessorChain bookCateProcessorChain;

    @Autowired
    private BookIntroProcessorChain bookIntroProcessorChain;

    @Autowired
    private BookChapterListProcessorChain bookChapterListProcessorChain;

    @Autowired
    private BookContentProcessorChain bookContentProcessorChain;

    @Override
    public ProcessorChain getProcessorChain(BookRule rule) {
        ProcessorChain chain1 = bookIndexProcessorChain.initRule(rule).initNext(null);
        ProcessorChain chain2 = bookCateProcessorChain.initRule(rule).initNext(chain1);
        ProcessorChain chain3 = bookIntroProcessorChain.initRule(rule).initNext(chain2);
        ProcessorChain chain4 = bookChapterListProcessorChain.initRule(rule).initNext(chain3);
        ProcessorChain chain5 = bookContentProcessorChain.initRule(rule).initNext(chain4);

        return chain5;
    }

    @Value("${crawler.processor.book.retryTimes:3}")
    private int retryTimes;

    @Value("${crawler.processor.book.sleepTime:1000}")
    private int sleepTime;

    @Override
    public Site getSite(BookRule rule) {
        return Site.me().setDomain(rule.getDomain())
                .setRetryTimes(retryTimes)
                .setSleepTime(sleepTime);
    }
}
