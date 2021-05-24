package com.fermedu.crawler.pipeline;

import com.fermedu.crawler.database.DbServiceAbstract;
import com.fermedu.crawler.entity.ExtractedEntity;
import com.guguskill.common.util.ListUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-16 01:14
 * @Author: JustThink
 * @Description: 具有抽象父类EntityDbServiceAbstract的所有写入db的方法。
 * 子类需要Override secondary key
 * @Include:
 **/
@Slf4j
public abstract class PipelineChainAbstract<T extends ExtractedEntity> extends DbServiceAbstract<T> implements PipelineChain, Pipeline {

    @Getter
    protected PipelineChainAbstract<T> next;

    protected abstract Class<T> getExtractedEntityClz();

    @Override
    public PipelineChainAbstract<T> initNext(PipelineChainAbstract next) {
        this.next = next;
        return this;
    }

    /***
     * @Description
     * @Params * @param Task task 是spider对象，获取UUID和获取site的接口。
     * @Return
     **/
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<T> list = resultItems.get(this.getExtractedEntityClz().getSimpleName());
        if (CollectionUtils.isEmpty(list)) { // 没有对应当前链的结果
            log.info(this.getClass().getSimpleName() + " did not find any " + this.getExtractedEntityClz().getSimpleName() + " in resultItems" + resultItems);
        } else {
            List<T> affectedList = super.addOrUpdateList(list);
            log.info(this.getClass().getSimpleName() + " found " + this.getExtractedEntityClz().getSimpleName() + " in resultItems" + resultItems + "\n"
                    + affectedList + " was updated.");
        }

        if (this.next != null) { // ListUtil 会对list迭代（literator），迭代过程中如果修改list，会报错java.util.ConcurrentModificationException
            next.process(resultItems, task);
        } else {
        }
    }
}
