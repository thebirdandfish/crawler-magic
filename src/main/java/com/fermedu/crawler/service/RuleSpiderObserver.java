package com.fermedu.crawler.service;

import java.util.Observable;
import java.util.Observer;

/**
 * @Program: book-crawler
 * @Create: 2021-02-03 16:58
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface RuleSpiderObserver extends Observer {
    @Override
    void update(Observable o, Object arg);
}
