package com.fermedu.crawler.controller;

import com.fermedu.crawler.entity.RuleControl;
import com.fermedu.crawler.entity.RuleGeneric;
import com.fermedu.crawler.repository.RuleControlRepository;
import com.guguskill.common.exception.GuNotFoundException;
import com.guguskill.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2021-01-25 23:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RestController
@Slf4j
public class RuleControlOperationController {

    @Autowired
    private RuleControlRepository ruleControlRepository;

    @GetMapping(path = {"/ruleControl/turnOn"})
    public ResultVo<RuleControl> ruleControlTurnOn(
            @RequestParam(name = "domain") String domain,
            Map<String, Object> map
    ) {
        RuleControl ruleControl = ruleControlRepository.findByDomain(domain)
                .orElseThrow(() -> new GuNotFoundException(domain + " is not found."));

        ruleControl.setRuleStatus(RuleGeneric.Status.ON);
        RuleControl saved = ruleControlRepository.saveAndFlush(ruleControl);

        return ResultVo.success(saved);
    }

    @GetMapping(path = {"/ruleControl/turnOff"})
    public ResultVo<RuleControl> ruleControlTurnOff(
            @RequestParam(name = "domain") String domain,
            Map<String, Object> map
    ) {
        RuleControl ruleControl = ruleControlRepository.findByDomain(domain)
                .orElseThrow(() -> new GuNotFoundException(domain + " is not found."));

        ruleControl.setRuleStatus(RuleGeneric.Status.OFF);
        RuleControl saved = ruleControlRepository.saveAndFlush(ruleControl);

        return ResultVo.success(saved);
    }
}
