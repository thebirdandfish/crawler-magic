package com.fermedu.crawler.monitor;

import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

/**
 * @Program: book-crawler
 * @Create: 2021-01-02 21:47
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface CrawlerSpiderStatusMXBean extends SpiderStatusMXBean {
    void resetDuplicationCheck();

    Long getRuleId();

    String getDomain();

    String getSpiderUuid();

    SpiderStatusEnum getStatusEnum();

    void startSpiderByRunAsync();

    void stopSpiderAndDestroy();
}
