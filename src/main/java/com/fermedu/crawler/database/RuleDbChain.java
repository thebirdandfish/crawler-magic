package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleGeneric;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-02-04 00:16
 * @Author: JustThink
 * @Description: 所有实现本接口的实现类，都会被 “ 自动” 加入到链式bean中（ruleDbServiceChain1）
 * @Include:
 **/
public interface RuleDbChain<T extends RuleGeneric> {
    RuleDbChainAbstract<T> initNext(RuleDbChainAbstract<RuleGeneric> next);

    RuleGeneric findByDomainCallNext(String domain);

    List<String> accumulateDomainCallNext(List<String> pastDomainList);

    List<Long> accumulateIdCallNext(List<Long> pastIdList);
}
