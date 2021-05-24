package com.fermedu.crawler.entity;

/**
 * @Program: book-crawler
 * @Create: 2020-12-16 02:24
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface ExtractedEntity extends EntityGeneric<Long> {
    Long getId();

    Long getRuleId();

    void setId(Long id);

    void setRuleId(Long ruleId);
}
