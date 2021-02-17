package com.fermedu.crawler.repository;

import com.fermedu.crawler.entity.BookRule;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 15:54
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Repository
public interface BookRuleRepository extends JpaRepository<BookRule, Long> {

    Optional<BookRule> findByDomainContaining(String domain);
}
