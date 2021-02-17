package com.fermedu.crawler.config;

import com.fermedu.crawler.entity.RuleGeneric;
import lombok.extern.slf4j.Slf4j;

/**
 * @Program: book-crawler
 * @Create: 2021-02-07 13:10
 * @Author: JustThink
 * @Description: Book类目下的配置
 * @Include:
 **/
@Slf4j
public abstract class ClassificationAbstract<T extends RuleGeneric> implements ClassificationConfig<T> {
    protected abstract Class<T> ruleClz();

    @Override
    public boolean ifSameClassification(Class<T> ruleClz) {
        if (this.ruleClz().equals(ruleClz)) {
            return true;
        }
        return false;
    }
}
