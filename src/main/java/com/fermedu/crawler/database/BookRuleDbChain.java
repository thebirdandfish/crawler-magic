package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.repository.BookRuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 16:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class BookRuleDbChain extends RuleDbChainAbstract<BookRule> implements RuleDbChain<BookRule> {

    @Autowired
    private BookRuleRepository repository;

    @Override
    protected BookRuleRepository getRepository() {
        return repository;
    }

    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"bookSourceName", "domain"};
    }

    @Override
    protected BookRule findByDomain(String domain) {
        return repository.findByDomainContaining(domain).orElseGet(null);
    }

}
