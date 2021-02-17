package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.SpiderEntity;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-01-26 01:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderDbService {

    List<SpiderEntity> findAll();

    SpiderEntity addOrUpdateOne(SpiderEntity spiderEntity);

    List<SpiderEntity> addOrUpdateList(List<SpiderEntity> spiderEntityList);
}
