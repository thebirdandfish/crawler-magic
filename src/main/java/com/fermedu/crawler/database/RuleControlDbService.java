package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleControl;
import com.fermedu.crawler.entity.RuleGeneric;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 18:28
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface RuleControlDbService {
    List<RuleControl> findAll();
}
