package com.fermedu.crawler.config;

import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.processor.ProcessorChain;
import com.guguskill.common.util.BeanFactoryUtil;
import us.codecraft.webmagic.Site;

import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-02-08 17:46
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface ClassificationConfig<T extends RuleGeneric> {

    boolean ifSameClassification(Class<T> ruleClz);

    ProcessorChain getProcessorChain(T rule);

    PipelineChain getPipelineDbChain();

    Site getSite(T rule);

    static ClassificationConfig get(Class<? extends RuleGeneric> ruleClz) {
        Map<String, ClassificationConfig> beans = BeanFactoryUtil.getBeansOfType(ClassificationConfig.class);

        for (Map.Entry<String, ClassificationConfig> entry : beans.entrySet()) {
            if (entry.getValue().ifSameClassification(ruleClz)) {
                return entry.getValue();
            }
        }

        return null;
    }
}
