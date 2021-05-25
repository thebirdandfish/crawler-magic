package com.fermedu.crawler.service;

import com.fermedu.crawler.database.RuleDbFacade;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.enumeration.SpiderStatusEnum;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.guguskill.common.enumeration.ResultEnum;
import com.guguskill.common.exception.GuInternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Spider;

import java.util.Date;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 14:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class SpiderRunnerImpl implements SpiderRunner {

    @Autowired
    private RuleDbFacade ruleDbFacadeImpl;

    @Autowired
    private SpiderCreator spiderCreatorImpl;

    @Autowired
    private LocalSpiderQueryService localSpiderQueryServiceImpl;

    /** call spiderDen to find a spider by domain.
     * Method is invoked by "destroy spider" and "create spider" */
    private CrawlerSpiderStatusMXBean findSpiderStatusMXBeanByDomain(String domain) {
        CrawlerSpiderStatusMXBean spiderStatusByDomain = localSpiderQueryServiceImpl.findLocalSpiderStatusByDomain(domain);
        if (spiderStatusByDomain == null) {
            String msg = String.format("No CrawlerSpiderStatusMXBean is found by %s", domain);
            log.info(msg);
            throw new GuInternalException(ResultEnum.VALUE_NULL.getCode(), msg);
        }
        log.info("domain {} corresponds to CrawlerSpiderStatusMXBean {}, we will run this shortly.", domain, spiderStatusByDomain.getSpiderUuid());
        return spiderStatusByDomain;
    }

    /** destroy a pider by domain, by calling spiderDen */
    private void destroySpiderByDomain(String domain) {
        CrawlerSpiderStatusMXBean spiderStatusMXBeanByDomain = this.findSpiderStatusMXBeanByDomain(domain);

        spiderStatusMXBeanByDomain.stopSpiderAndDestroy();
        log.warn("Spider {} has been destroyed.",domain);
    }

    /**
     * 通过domain找rule， 通过rule创建爬虫。
     * 爬虫的实例不需要返回。runner会从monitor里面找
     */
    private Spider createSpider(String domain, boolean reset) {
        // 找到rule，启动爬虫
        RuleGeneric rule = ruleDbFacadeImpl.findByDomain(domain); // find rule
        Spider spider = spiderCreatorImpl.createSpider(rule, reset);// create and run spider by the rule

        log.info(new Date() + " 爬虫被创建\n"
                + "SpiderUUID: " + spider.getUUID() + "; Domain: " + domain);
        return spider;
    }


    @Async
    @Transactional
    @Override
    public void turnOnLocalSpider(String domain, SpiderStatusEnum spiderStatus, boolean reset) {
        // 更新spiderDbService
        localSpiderQueryServiceImpl.setLocalSpiderEntityOnInit(domain);

        if (spiderStatus.equals(SpiderStatusEnum.UNCREATED)) { // 2. 需要先create
            log.info("Spider is not yet created for domain {}, now creating it.", domain);
        } else { // 1.已经有spider，直接运行
            log.warn("WARNING! Spider {} is not on, but it appears to be already created. Preparing to destroy the spider.", domain);
            this.destroySpiderByDomain(domain);
        }
        Spider spider = this.createSpider(domain, reset);
        spider.runAsync();
        log.info("spider is running aync now. Local UUID: {}", spider.getUUID());
    }

    @Async
    @Transactional
    @Override
    public void turnOffLocalSpider(String domain) {
        // 更新spiderDbservice
        localSpiderQueryServiceImpl.setLocalSpiderEntityOnStop(domain);

        // 停止爬虫
        localSpiderQueryServiceImpl.findLocalSpiderStatusByDomain(domain)
                .stopSpiderAndDestroy(); // stop the spider
    }

}
