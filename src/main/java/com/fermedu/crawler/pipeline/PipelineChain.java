package com.fermedu.crawler.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Program: book-crawler
 * @Create: 2020-12-16 01:36
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface PipelineChain extends Pipeline {

    PipelineChainAbstract initNext(PipelineChainAbstract next);

    @Override
    void process(ResultItems resultItems, Task task);
}
