package com.fermedu.crawler.service;

import com.fermedu.crawler.config.ClassificationConfig;
import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.monitor.SpiderCommonDecorator;
import com.fermedu.crawler.util.UrlFastUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Program: book-crawler
 * @Create: 2021-02-08 16:43
 * @Author: JustThink
 * @Description: SpiderRunner依赖于SpiderCreator
 * @Include:
 **/
@Service
@Slf4j
public class SpiderCreatorImpl implements SpiderCreator {

    @Autowired
    private SpiderCommonDecorator spiderCommonDecoratorImpl;

    @Autowired
    private LocalSpiderQueryService localSpiderQueryServiceImpl;

    /***
     * @Description turnOnRule 按照给定的rule ，定制processor和rule（具体的url从rule的记录中获取）
     * 启动爬虫
     * 跟一个爬虫完整生命周期。 一个rule对应一个spider. 一个spider对应一个processor
     * @Params * @param rule
     * @Return void
     **/
    @Override
    public Spider createSpider(RuleGeneric rule, boolean reset) {
        String uuid = localSpiderQueryServiceImpl.findLocalSpiderEntityByDomain(rule.getDomain()).getUuid();

        ClassificationConfig specificConfig = ClassificationConfig.get(rule.getClass());
        PageProcessor pageProcessor = specificConfig.getProcessorChain(rule);
        PipelineChain pipeline = specificConfig.getPipelineDbChain();

        /** create spider */
        Spider spider = Spider
                .create(pageProcessor) // 会初始化spider对象内的pageProcessor，site，和domain
                .setUUID(uuid) // 从db中查询到的，预先生成的uuid
                .addPipeline(pipeline);

        /** enhance spider */
        this.spiderCommonDecoratorImpl.decorate(spider, reset); // 这个过程中，FermSpiderMonitor会被用于修饰spider，并且上面spider初始化的domain和UUID可以通过FermSpiderMonitor获取

        /** starting request (do this after the scheduler) */
        String indexUrl = UrlFastUtil.setTimestampParamToGetUrl(rule.getDomain());
        spider.addUrl(indexUrl);

        return spider;
    }
}
