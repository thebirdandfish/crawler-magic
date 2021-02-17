package com.fermedu.crawler.database;

import com.fermedu.crawler.config.LocalConfig;
import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.repository.SpiderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Program: book-crawler
 * @Create: 2021-01-01 13:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SpiderDbServiceImpl extends DbServiceAbstract<SpiderEntity> implements SpiderDbService {
    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"spiderUuid"};// ruleId可以重复，spiderUuid 是唯一标识
    }

    @Autowired
    private SpiderRepository repository;

    @Override
    protected SpiderRepository getRepository() {
        return this.repository;
    }

    @Override
    public List<SpiderEntity> findAll() {
        return repository.findAll();
    }
}
