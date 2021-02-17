package com.fermedu.crawler.monitor;

import us.codecraft.webmagic.Spider;

/**
 * @Program: book-crawler
 * @Create: 2021-02-07 19:04
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderCommonDecorator {
    void decorate(Spider spider, boolean reset);
}
