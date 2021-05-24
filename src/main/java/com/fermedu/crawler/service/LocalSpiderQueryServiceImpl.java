package com.fermedu.crawler.service;

import com.fermedu.crawler.config.LocalConfig;
import com.fermedu.crawler.database.RuleDbFacade;
import com.fermedu.crawler.database.SpiderDbService;
import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import com.fermedu.crawler.factory.SpiderEntityFactory;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.fermedu.crawler.monitor.SpiderDenService;
import com.fermedu.crawler.repository.SpiderRepository;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 15:52
 * @Author: JustThink
 * @Description: 同步SpiderDenService和SpiderDbService，将spiderStatus封装成更高级的spiderEntity
 * @Include:
 **/
@Service
@Slf4j
public class LocalSpiderQueryServiceImpl implements LocalSpiderQueryService {

    @Autowired
    private SpiderDenService spiderDenServiceImpl;

    @Autowired
    private SpiderRepository spiderRepository;

    @Autowired
    private SpiderEntityFactory spiderEntityFactoryImpl;

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;

    @Autowired
    private LocalConfig localConfig; // 本机ip和port

    /***
    * @Description 每个public方法首先调用。调用后，本机所有spider（已有或者uncreated）均会被更新至数据库。之后调用SpiderDbService可以取得本机对应的SpiderEntity
    * @Params * @param null
    * @Return 仅本机对应的所有spiderEntities
    **/
    private Map<String, SpiderEntity> initSyncLocalSpiderEntities() {
        /** 1. 从rule查找所有记录，每个rule对应一个本机spider（UUID）(domain+ip+port) 。如果对应的UUID还没有初始化，则先初始化UUID。此处是唯一一处初始UUID的地方 */
        // 1. 找到所有rule的domainList
        List<String> domainList = ruleDbFacadeImpl.findAllDomain();

        // 2. 找到local的spiderStatuses
        Map<String, CrawlerSpiderStatusMXBean> allSpiderStatuses = this.spiderDenServiceImpl.getAllSpiderStatuses();

        // 3. 使用domain，uuid，spiderStatus生成对应的entity
        List<SpiderEntity> generatedSpiderEntities = domainList.stream().map(domain -> {
            String uuid = SpiderEntityFactory.combineUuidFrom(domain, localConfig.getIpAddress(), localConfig.getPort());
            CrawlerSpiderStatusMXBean spiderStatus = allSpiderStatuses.get(domain);
            SpiderEntity spiderEntity = spiderEntityFactoryImpl.getSpiderEntityFrom(domain, uuid, spiderStatus);
            return spiderEntity;
        }).collect(Collectors.toList());

        // 4. 写入Db
        List<SpiderEntity> localSpiderEntities = spiderRepository.saveAll(generatedSpiderEntities);

        // 5. 转换为HashMap
        Map<String, SpiderEntity> localSpiderEntityMap = localSpiderEntities.stream()
                .collect(Collectors.toMap(e -> e.getDomain(), e -> e));

        log.info("localSpiderEntityMap: {}", localSpiderEntityMap);

        return localSpiderEntityMap;
    }

    /***
     * @Description 查询domain对应的本机的spiderEntity
     * @Params * @param null
     * @Return
     **/
    @Override
    public Map<String, SpiderEntity> findLocalSpiderEntities() {
        Map<String, SpiderEntity> entityMap = this.initSyncLocalSpiderEntities();
        return entityMap;
    }


    /***
    * @Description 查询domain对应的本机的spiderEntity
    * @Params * @param domain
    * @Return 
    **/
    @Override
    public SpiderEntity findLocalSpiderEntityByDomain(String domain) {
        Map<String, SpiderEntity> entityMap = this.initSyncLocalSpiderEntities();

        SpiderEntity resultEntity = entityMap.get(domain);

        if (resultEntity == null) {
            String msg = "查询spiderEntity的domain参数" + domain + "没有找到对应spiderEntity";
            log.error(msg);
            throw new GuInternalException(ResultEnum.VALUE_NULL.getCode(), msg);
        }

        return resultEntity;
    }

    @Override
    public CrawlerSpiderStatusMXBean findLocalSpiderStatusByDomain(String domain) {
        this.initSyncLocalSpiderEntities();

        return spiderDenServiceImpl.getSpiderStatusByDomain(domain);
    }

    @Override
    public Map<String, CrawlerSpiderStatusMXBean> findLocalSpiderStatuses() {
        this.initSyncLocalSpiderEntities();

        return spiderDenServiceImpl.getAllSpiderStatuses();
    }

    /***
    * @Description 1. 找到domain对应的本机spiderEntity，2.该状态（enum和lastinit）3.更新到spiderDb
    * @Params * @param null
    * @Return
    **/
    @Override
    public SpiderEntity setLocalSpiderEntityOnInit(String domain) {
        SpiderEntity spiderEntity = this.findLocalSpiderEntityByDomain(domain);
        spiderEntity.setSpiderStatus(SpiderStatusEnum.INIT);
        spiderEntity.setLastInit(new Date());

        return spiderRepository.save(spiderEntity);
    }

    /***
     * @Description 1. 找到domain对应的本机spiderEntity，2.该状态（enum和lastinit）3.更新到spiderDb
     * @Params * @param null
     * @Return
     **/
    @Override
    public SpiderEntity setLocalSpiderEntityOnStop(String domain) {
        SpiderEntity spiderEntity = this.findLocalSpiderEntityByDomain(domain);
        spiderEntity.setSpiderStatus(SpiderStatusEnum.STOPPED);
        spiderEntity.setLastInit(new Date());

        return spiderRepository.save(spiderEntity);
    }
}
