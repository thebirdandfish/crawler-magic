package com.fermedu.crawler.controller;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.monitor.CrawlerSpiderStatusMXBean;
import com.fermedu.crawler.monitor.SpiderDenService;
import com.fermedu.crawler.repository.BookRuleRepository;
import com.guguskill.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-01-25 23:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RestController
@Slf4j
public class ResetDuplicationCheckController {

    @Autowired
    private SpiderDenService spiderDenServiceImpl;

    @GetMapping(path = {"/duplicationCheck/reset"})
    public ResultVo ruleList(
            @RequestParam(name = "domain") String domain
            ) {
        CrawlerSpiderStatusMXBean spiderStatusByDomain = spiderDenServiceImpl.getSpiderStatusByDomain(domain);
        spiderStatusByDomain.resetDuplicationCheck();

        return ResultVo.success();
    }
}
