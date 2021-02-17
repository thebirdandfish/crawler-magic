package com.fermedu.crawler.repository;

import com.fermedu.crawler.entity.RuleControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2021-01-01 00:57
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Repository
public interface RuleControlRepository extends JpaRepository<RuleControl, Long> {
}
