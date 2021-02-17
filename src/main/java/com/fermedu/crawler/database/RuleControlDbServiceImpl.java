package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleControl;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.repository.RuleControlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2021-01-22 22:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class RuleControlDbServiceImpl extends DbServiceAbstract<RuleControl> implements RuleControlDbService {
    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"domain"};
    }

    @Autowired
    private RuleControlRepository repository;

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;

    @Override
    protected RuleControlRepository getRepository() {
        return this.repository;
    }

    /**
     * 从rule更新记录，仅仅影响与ruleId相关字段。应该由上级模块RuleSpiderObservable调用
     */
    private List<RuleControl> initUpdateWithAllRules() {
        List<String> allDomains = ruleDbFacadeImpl.findAllDomain();

        Set<String> ruleDomains = allDomains.stream().collect(Collectors.toSet());
        Set<String> existingDomains = repository.findAll().stream().map(RuleControl::getDomain).collect(Collectors.toSet()); // ruleControl现有的

        ruleDomains.removeAll(existingDomains); // 保留ruleLong多出来的部分
        /** 补足缺失的ruleLongs */
        List<RuleControl> toBeAdded = ruleDomains.stream().map(ruleDomain -> {
            RuleControl ruleControl = new RuleControl();
            ruleControl.setDomain(ruleDomain);
            ruleControl.setRuleStatus(RuleGeneric.Status.OFF);
            return ruleControl;
        }).collect(Collectors.toList());

        return this.addOrUpdateList(toBeAdded);// 添加到db
    }


    /**
     * ruleId 升序排列
     */
    @Override
    public List<RuleControl> findAll() {
        this.initUpdateWithAllRules();

        List<RuleControl> ruleControls = repository.findAll();
        return ruleControls; // 用rule更新了rule_control表后，返回所有rule_control记录。
    }
}
