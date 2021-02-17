package com.fermedu.crawler.repository;

import com.fermedu.crawler.entity.Replaced;
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
public interface ReplacedRepository extends JpaRepository<Replaced, Long> {
}
