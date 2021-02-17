package com.fermedu.crawler.service;

import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.database.*;
import com.fermedu.crawler.entity.RuleControl;
import com.fermedu.crawler.entity.SpiderEntity;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import com.guguskill.common.util.CopyUtil;
import com.guguskill.util.BeanFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2021-01-01 00:56
 * @Author: JustThink
 * @Description: 对于switchDbService的包装和增强。通过：1定时，2外来api请求，更新switchDbService。每次方法的调用，都应该调用updateDbWithQueriedSpiderStatuses()
 * 同时，也定时查找db中不一致的ruleStatus和spiderStatus
 * <p>
 * 找到所有rule记录，通过所有的domainList，补全ruleControl表，补全spiderEntity表
 * @Include:
 **/
@Slf4j
@Service
public class RuleSpiderUpdater extends Observable implements RuleSpiderObservable {

    private Map<String, RuleSpiderDao> ruleSpiderDaoMap = Collections.synchronizedMap(new HashMap<>());

    @Autowired
    private RuleControlDbService ruleControlDbServiceImpl;

    @Autowired
    private LocalSpiderQueryService localSpiderQueryServiceImpl;

    /***
     * @Description SwitchObservable 构造函数，添加observer
     * @Params * @param
     * @Return
     **/
    public RuleSpiderUpdater() { // 构造函数，初始化observer
        super.addObserver(BeanFactoryUtil.getBeanByImplClass(RuleRunner.class));
    }

    /**
     * 1获取spider状态，2更新db，3找到状态不一致的list（如有，通知observer）
     */
    @Scheduled(cron = "10/30 * * * * ?") //cron 表达式
    private void timelySyncAndNotify() {
        /** 更新latestUpdateTime */
        Map<String, RuleSpiderDao> newMap = this.getLatestRuleSpiderDaoList();

        if (newMap == null || newMap.size() == 0) {
            String msg = "Map<String, RuleSpiderDao> 为空，请检查rule表是否有记录";
            log.error(msg);
            throw new GuInternalException(ResultEnum.VALUE_NULL.getCode(), msg);
        } else {
            /**set changed  and notify */
            if (newMap.equals(this.ruleSpiderDaoMap)) { // 与之前的没有变化
                log.trace(new Date() + " ruleSpiderDaoMap 没有发生变化，不通知观察者");
            } else { // 与之前的不同
                log.trace(new Date() + " ruleSpiderDaoMap 有变化，通知观察者。\n"
                        + "newMap: " + newMap + "\n"
                        + "oldMap: " + this.ruleSpiderDaoMap);
                this.ruleSpiderDaoMap.putAll(newMap);

                this.setChanged();
                this.notifyObservers(this.ruleSpiderDaoMap);
            }
        }
    }


    /***
     * @Description combineRuleControlsWithSpiders 将两个list，按照ruleId相等的结合在一起，ruleControl如果没有对应的spiderEntity，则新建。
     * @Params * @param ruleControls
     * @param spiderEntities
     * @Return java.util.List<com.fermedu.crawler.dao.RuleSpiderDao>
     **/
    private static Map<String, RuleSpiderDao> combineRuleControlsWithLocalSpiders(List<RuleControl> ruleControls, Map<String, SpiderEntity> spiderEntities) {
        /** RuleSwitch肯定有记录，并且多于SpiderEntities */
        Map<String, RuleSpiderDao> ruleSpiderDaos = ruleControls.stream().map(ruleControl -> {
            String domain = ruleControl.getDomain();
            SpiderEntity spiderEntity = spiderEntities.get(domain);

            RuleSpiderDao ruleSpiderDao = new RuleSpiderDao();
            CopyUtil.copyNonNullProperties(ruleControl, ruleSpiderDao);
            CopyUtil.copyNonNullProperties(spiderEntity, ruleSpiderDao);

            return ruleSpiderDao;
        }).collect(Collectors.toMap(e -> e.getDomain(), e -> e));

        return ruleSpiderDaos;
    }

    /**
     * 定时器调用入口方法
     * 1. 更新ruleControl
     * 2. 更新spiderEntities（local部分）
     */
    private Map<String, RuleSpiderDao> getLatestRuleSpiderDaoList() { // update db with rules（rule表） and spiderStatuses（jvm查询到的）
        // 1. 使用所有rule的domainList，更新ruleControl
        List<RuleControl> ruleControls = ruleControlDbServiceImpl.findAll();

        // 2. 使用所有rule的domainList，更新spiderEntities（local部分）
        Map<String, SpiderEntity> localSpiderEntities = localSpiderQueryServiceImpl.findLocalSpiderEntities();

        // 3. 结合ruleControls和localSpiderEntities
        Map<String, RuleSpiderDao> combined = combineRuleControlsWithLocalSpiders(ruleControls, localSpiderEntities);

        return combined;
    }

    @Override
    public Map<String, RuleSpiderDao> getLocalRuleSpiderDao() {
        return this.ruleSpiderDaoMap;
    }
}
