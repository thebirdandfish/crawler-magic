package com.fermedu.crawler.controller;

import com.fermedu.crawler.config.LocalConfig;
import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.entity.SpiderEntity;
import com.fermedu.crawler.repository.SpiderRepository;
import com.fermedu.crawler.service.RuleSpiderObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-01-25 23:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Controller
@Slf4j
public class SpiderStatusMonitorController {

    @Autowired
    private RuleSpiderObservable ruleSpiderUpdater;

    @Autowired
    private SpiderRepository spiderRepository;

    @Autowired
    private LocalConfig localConfig;

    @GetMapping(path = {"/scraper/status/monitor"})
    public ModelAndView scraperStatusMonitor(
            Map<String, Object> map
    ) {
        Map<String, RuleSpiderDao> localRuleSpiderDaoMap = ruleSpiderUpdater.getLocalRuleSpiderDao();
        map.put("localRuleSpiderDaoMap", localRuleSpiderDaoMap);

        List<SpiderEntity> spiderEntities = spiderRepository.findAll();
        map.put("spiderEntities", spiderEntities);

        map.put("ipAddress", localConfig.getIpAddress());
        map.put("port", localConfig.getPort());

        return new ModelAndView("spider_status/overview", map);
    }
}
