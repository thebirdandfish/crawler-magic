package com.fermedu.crawler.controller;

import com.fermedu.crawler.entity.BookRule;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
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
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Program: book-crawler
 * @Create: 2021-01-25 23:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Controller
@Slf4j
public class XpathEvaluatorController {
//todo download one page and evaluate Xpath.

    @GetMapping(path = {"/xpathEvaluator/download/parse"})
    public ModelAndView ruleList(
            @RequestParam(name = "url") String url,
            @RequestParam(name = "xpath") String xpath,
            Map<String, Object> map) {

        map.put("url", url);
        map.put("xpath", xpath);

        AtomicReference<String> htmlRef = new AtomicReference<>();
        AtomicReference<String> exceptionRef = new AtomicReference<>();
        AtomicReference<List<String>> selectedListRef = new AtomicReference<>();

        Spider.create(new PageProcessor() {
            @Override
            public void process(Page page) {
                htmlRef.set(page.getHtml().toString());

                try {
                    Selectable selected = page.getHtml().xpath(xpath);
                    selectedListRef.set(selected.all());
                } catch (Exception e) {
                    exceptionRef.set(e.getMessage());
                }
            }

            @Override
            public Site getSite() {
                return Site.me().setRetryTimes(3).setSleepTime(100);
            }
        }).addUrl(url).run();

        map.put("html", htmlRef.get());

        map.put("selectedList", selectedListRef.get());
        map.put("exception", exceptionRef.get());

        return new ModelAndView("/xpath_evaluator/result", map);
    }
}
