package com.fermedu.crawler.pipeline.book.chain;

import com.fermedu.crawler.entity.book.BookChapter;
import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.repo.BookContentRepository;
import com.fermedu.crawler.entity.book.BookContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 16:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class BookContentDbChain extends PipelineChainAbstract<BookContent> implements PipelineChain {

    @Override
    protected Class<BookContent> getExtractedEntityClz() {
        return BookContent.class;
    }

    @Autowired
    private BookContentRepository repository;
    @Override
    protected BookContentRepository getRepository() {
        return this.repository;
    }

    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"contentUrl"};
    }
}
