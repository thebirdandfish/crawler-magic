package com.fermedu.crawler.enumeration;

import us.codecraft.webmagic.Spider;

/**
 * @Program: book-crawler
 * @Create: 2021-02-05 16:49
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public enum SpiderStatusEnum {

    INIT(0),
    RUNNING(1),
    STOPPED(2),
    UNCREATED(3);

    private int value;

    SpiderStatusEnum(int value) {
        this.value = value;
    }

    public static SpiderStatusEnum fromWebmagicSpiderStatus(Spider.Status webmagicSpiderStatus) {
        if (webmagicSpiderStatus == null) {
            return SpiderStatusEnum.UNCREATED;
        }

        switch (webmagicSpiderStatus) {
            case Running:
                return SpiderStatusEnum.RUNNING;
            case Stopped:
                return SpiderStatusEnum.STOPPED;
            case Init:
                return SpiderStatusEnum.INIT;
        }
        return SpiderStatusEnum.INIT;
    }
}
