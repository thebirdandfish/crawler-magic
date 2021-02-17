package com.fermedu.crawler.pipeline.book.chain;

import com.fermedu.crawler.entity.book.BookCate;
import com.fermedu.crawler.pipeline.PipelineChain;
import com.fermedu.crawler.pipeline.PipelineChainAbstract;
import com.fermedu.crawler.pipeline.book.repo.BookChapterRepository;
import com.fermedu.crawler.entity.book.BookChapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 16:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class BookChapterDbChain extends PipelineChainAbstract<BookChapter> implements PipelineChain {

    @Override
    protected Class<BookChapter> getExtractedEntityClz() {
        return BookChapter.class;
    }

    @Autowired
    private BookChapterRepository repository;

    @Override
    protected String[] getSecondaryKeyArray() {
        return new String[]{"chapterUrl"};
    }

    @Override
    protected BookChapterRepository getRepository() {
        return this.repository;
    }

    @Override
    public List<BookChapter> addOrUpdateList(List<BookChapter> entityList) {
        return super.addOrUpdateList(entityList);
    }

    /***
     * @Description addOrUpdateRule 不允许重复的字段有：id, ruleId
     * @Params * @param rule
     * @Return java.lang.Long
     **/


    @Override
    public BookChapter addOrUpdateOne(BookChapter bookChapter) {
        return super.addOrUpdateOne(bookChapter);
    }

    @Override
    public BookChapter findOne(BookChapter example) {
        return super.findOne(example);
    }

    @Override
    public List<BookChapter> findList(BookChapter anyKeyFieldMatching) {
        return super.findList(anyKeyFieldMatching);
    }
}
