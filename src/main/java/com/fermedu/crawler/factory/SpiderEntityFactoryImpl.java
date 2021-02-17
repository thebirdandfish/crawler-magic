package com.fermedu.crawler.factory;

import com.fermedu.crawler.config.LocalConfig;
import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.guguskill.common.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 00:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SpiderEntityFactoryImpl implements SpiderEntityFactory {

    @Autowired
    private LocalConfig localConfig;

    @Override
    public SpiderEntity getSpiderEntityFrom(String domain, String spiderUuid, CrawlerSpiderStatusMXBean spiderStatus) {
        SpiderEntity spiderEntity = new SpiderEntity();

        spiderEntity.setDomain(domain);
        spiderEntity.setUuid(spiderUuid);
        spiderEntity.setIpAddress(localConfig.getIpAddress());
        spiderEntity.setPort(localConfig.getPort());

        if (spiderStatus == null || spiderStatus.getDomain() == null) {//spiderStatus 不存在
            spiderEntity.setSpiderStatus(SpiderStatusEnum.UNCREATED); // 找不到spider， 视为uncreated，表示没有创建
        } else { //spiderStatus 存在

            spiderEntity.setLeftPageCount(spiderStatus.getLeftPageCount());
            spiderEntity.setTotalPageCount(spiderStatus.getTotalPageCount());
            spiderEntity.setSuccessPageCount(spiderStatus.getSuccessPageCount());
            spiderEntity.setErrorPageCount(spiderStatus.getErrorPageCount());
            spiderEntity.setErrorPagesListJson(ListUtil.toJsonString(spiderStatus.getErrorPages()));
            spiderEntity.setSpiderStatus(spiderStatus.getStatusEnum()); // Spider.Status 为null时，表示爬虫未创建
            spiderEntity.setThread(spiderStatus.getThread());
            spiderEntity.setStartTime(spiderStatus.getStartTime());

            try {
                spiderEntity.setPagePerSecond(spiderStatus.getPagePerSecond());
            } catch (Exception e) {
                spiderEntity.setPagePerSecond(-1);
            }
        }

        return spiderEntity;
    }
}
