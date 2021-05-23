package com.fermedu.crawler.controller;

import com.fermedu.crawler.dao.RuleSpiderDao;
import com.fermedu.crawler.service.RuleSpiderObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ExtractedEntityLogsController {

    @GetMapping(path = {"/extracted/{cateName}/logs"})
    public ModelAndView overview(
            @PathVariable(name = "cateName") String cateName,
            @RequestParam(name = "domain") String domain,
            Map<String, Object> map) {
        // todo

        return new ModelAndView("rule_spider/overview", map);
    }
}
