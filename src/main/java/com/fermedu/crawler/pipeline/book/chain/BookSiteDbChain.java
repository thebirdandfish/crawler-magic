package com.fermedu.crawler.pipeline.book.chain;

import com.fermedu.crawler.entity.book.BookIntro;
import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.repo.BookSiteRepository;
import com.fermedu.crawler.entity.book.BookSite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 16:48
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class BookSiteDbChain extends PipelineChainAbstract<BookSite> implements PipelineChain {

    @Override
    protected Class<BookSite> getExtractedEntityClz() {
        return BookSite.class;
    }

    @Autowired
    private BookSiteRepository repository;

    @Override
    protected BookSiteRepository getRepository() {
        return this.repository;
    }

    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"indexSiteTitle","ruleId"};
    }
}
