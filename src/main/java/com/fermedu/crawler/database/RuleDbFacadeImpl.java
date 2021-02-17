package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleGeneric;
import com.guguskill.util.BeanFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Program: book-crawler
 * @Create: 2021-02-09 17:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class RuleDbFacadeImpl implements RuleDbFacade {

    /***
     * @Description allRuleDbService
     * @Params * @param
     * @Return com.fermedu.crawler.database.RuleDbService 所有实现了RuleDbService接口的链式bean
     **/
    private RuleDbChain ruleDbChains() {
        Map<String, RuleDbChainAbstract> ruleDbBeans = BeanFactoryUtil.getBeansOfType(RuleDbChainAbstract.class); // 从applicationContext中拿到beans，所有该接口的实现bean
        Iterator<Map.Entry<String, RuleDbChainAbstract>> iterator = ruleDbBeans.entrySet().iterator();

        AtomicReference<RuleDbChainAbstract> chainHolder = new AtomicReference<>();

        while (iterator.hasNext()) {
            RuleDbChainAbstract iterated = iterator.next().getValue();
            RuleDbChainAbstract accumulated = iterated.initNext(chainHolder.get());
            chainHolder.set(accumulated);
        }

        return chainHolder.get();
    }

    @Override
    public RuleGeneric findByDomain(String domain) {
        return this.ruleDbChains().findByDomainCallNext(domain);
    }

    @Override
    public List<String> findAllDomain() {
        List<String> result = this.ruleDbChains().accumulateDomainCallNext(null);
        return result;
    }

    @Override
    public List<Long> findAllId() {
        return this.ruleDbChains().accumulateIdCallNext(null);
    }
}
