package com.fermedu.crawler.pipeline.book.repo;

import com.fermedu.crawler.entity.book.BookChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 14:14
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Repository
public interface BookChapterRepository extends JpaRepository<BookChapter, Long> {
}
