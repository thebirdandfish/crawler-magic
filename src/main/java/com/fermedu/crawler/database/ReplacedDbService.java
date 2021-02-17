package com.fermedu.crawler.database;

import com.fermedu.crawler.entity.Replaced;
import com.fermedu.crawler.repository.ReplacedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 16:48
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class ReplacedDbService {

    @Autowired
    private ReplacedRepository replacedRepository;

    public List<Replaced> findAll() {
        return replacedRepository.findAll();
    }
}
