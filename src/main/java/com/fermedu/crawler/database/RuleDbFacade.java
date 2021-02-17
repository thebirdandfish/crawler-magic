package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.RuleGeneric;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-02-09 17:09
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface RuleDbFacade {
    RuleGeneric findByDomain(String domain); // 在所有的链式处理器中找到domain参数对应的类

    List<String> findAllDomain();

    List<Long> findAllId();
}
