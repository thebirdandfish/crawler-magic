package com.fermedu.crawler.controller;

import com.fermedu.crawler.config.BookClassificationConfig;
import com.fermedu.crawler.database.DbServiceAbstract;
import com.fermedu.crawler.entity.ExtractedEntity;
import com.fermedu.crawler.pipeline.PipelineChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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

    @Autowired
    private BookClassificationConfig bookClassificationConfig;

    @GetMapping(path = {"/extracted/{specification}/logs"})
    public ModelAndView extractedLogs(
            @PathVariable(name = "specification") String specification,
            @RequestParam(name = "domain") String domain,
            Map<String, Object> map) {

        map.put("specification", "book");
        map.put("domain", domain);

        List<ExtractedEntity> entityList = new ArrayList<>();
        AtomicReference<PipelineChain> chainRef = new AtomicReference<>(bookClassificationConfig.getPipelineDbChain());
        while (chainRef.get() != null) {
            Page recentUpdatePage = ((DbServiceAbstract) chainRef.get()).findRecentUpdatePage(0, 10);
            recentUpdatePage.stream().forEach(e -> entityList.add((ExtractedEntity) e));

            chainRef.set(chainRef.get().getNext());
        }

        map.put("entityList", entityList);

        return new ModelAndView("extracted/logs", map);
    }
}
