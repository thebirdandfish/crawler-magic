package com.fermedu.crawler.pipeline.book.chain;

import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.repo.BookCateRepository;
import com.fermedu.crawler.entity.book.BookCate;
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
public class BookCateDbChain extends PipelineChainAbstract<BookCate> implements PipelineChain {
    @Override
    protected Class<BookCate> getExtractedEntityClz() {
        return BookCate.class;
    }

    @Autowired
    private BookCateRepository repository;

    @Override
    protected BookCateRepository getRepository() {
        return this.repository;
    }

    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"cateUrl"};
    }
}
