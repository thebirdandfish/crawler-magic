package com.fermedu.crawler.pipeline.book.chain;

import com.fermedu.crawler.entity.book.BookContent;
import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.repo.BookIntroRepository;
import com.fermedu.crawler.entity.book.BookIntro;
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
public class BookIntroDbChain extends PipelineChainAbstract<BookIntro> implements PipelineChain {

    @Override
    protected Class<BookIntro> getExtractedEntityClz() {
        return BookIntro.class;
    }
    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"bookChapterListUrl","bookUrl"};
    }

    @Autowired
    private BookIntroRepository repository;

    @Override
    protected BookIntroRepository getRepository() {
        return this.repository;
    }
}
