package com.fermedu.crawler.controller;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.repository.BookRuleRepository;
import com.guguskill.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@Slf4j
public class RuleController {

    @Autowired
    private BookRuleRepository bookRuleRepository;

    @GetMapping(path = {"/rule/{specification}/list"})
    public ResultVo<BookRule> ruleList(
            @PathVariable(name = "specification") String specification
            ) {

        List<BookRule> all = bookRuleRepository.findAll();

        return ResultVo.success(all);
    }
}
