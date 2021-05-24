package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleGeneric;
import com.guguskill.common.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 16:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Slf4j
public abstract class RuleDbChainAbstract<T extends RuleGeneric> extends DbServiceAbstract<T, Long> implements RuleDbChain<T> {

    private RuleDbChainAbstract<RuleGeneric> next;

    @Override
    public RuleDbChainAbstract<T> initNext(RuleDbChainAbstract<RuleGeneric> next) {
        this.next = next;
        return this;
    }

    protected abstract T findByDomain(String domain);

    @Override
    public RuleGeneric findByDomainCallNext(String domain) {
        T ruleByDomain = this.findByDomain(domain);
        if (ruleByDomain == null && next != null) { // no match in current chian
            return next.findByDomainCallNext(domain);
        }
        return ruleByDomain;
    }

    @Override
    public List<String> accumulateDomainCallNext(List<String> pastDomainList) {
        List<String> currentDomainList = this.getRepository().findAll().stream().map(T::getDomain).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(pastDomainList)) {
            currentDomainList.addAll(pastDomainList);
        }

        if (this.next != null) {
            return next.accumulateDomainCallNext(currentDomainList);
        }
        return currentDomainList;
    }

    @Override
    public List<Long> accumulateIdCallNext(List<Long> pastIdList) {
        List<Long> currentIdList = this.getRepository().findAll().stream().map(T::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(pastIdList)) {
            currentIdList.addAll(pastIdList);
        }

        if (this.next != null) {
            return next.accumulateIdCallNext(currentIdList);
        }
        return currentIdList;
    }
}
