package com.fermedu.crawler.controller;

import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.service.RuleSpiderObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
public class RuleSpiderController {

    @Autowired
    private RuleSpiderObservable ruleSpiderUpdater;

    @GetMapping(path = {"", "/", "/index"})
    public ModelAndView index(Map<String, Object> map) {
        return this.overview(map);
    }

    @GetMapping(path = {"/ruleSpider/overview"})
    public ModelAndView overview(Map<String, Object> map) {
        Map<String, RuleSpiderDao> ruleSpiderDaoMap = ruleSpiderUpdater.getLocalRuleSpiderDao();
        map.put("ruleSpiderDaoMap", ruleSpiderDaoMap);

        return new ModelAndView("rule_spider/overview", map);
    }
}
