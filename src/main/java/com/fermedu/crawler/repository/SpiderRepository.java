package com.fermedu.crawler.repository;

import com.fermedu.crawler.entity.SpiderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Program: book-crawler
 * @Create: 2021-01-01 00:57
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Repository
public interface SpiderRepository extends JpaRepository<SpiderEntity, String> {
}
