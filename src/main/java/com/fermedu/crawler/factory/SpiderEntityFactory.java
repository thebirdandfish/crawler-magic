package com.fermedu.crawler.factory;

import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.guguskill.common.util.StringUtil;
import org.springframework.util.StringUtils;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 00:53
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SpiderEntityFactory {
    SpiderEntity getSpiderEntityFrom(String domain, String spiderUuid, CrawlerSpiderStatusMXBean spiderStatus);

    static String combineUuidFrom(String domain, String ipAddress, Integer port) {
        if (!StringUtils.hasText(ipAddress)) {
            ipAddress = "unknown";
        }
        String uuid = domain + "_" + ipAddress + "_" + port;
        return uuid;
    }
}
