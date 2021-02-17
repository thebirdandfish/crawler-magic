package com.fermedu.crawler.oncetemp;

import com.fermedu.crawler.pipeline.book.repo.BookSiteRepository;
import com.guguskill.util.BeanFactoryUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2020-12-14 23:11
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class OnceTemp {

    @Test
    public void temp() {
        Map<String, BookSiteRepository> beansOfType = BeanFactoryUtil.getBeansOfType(BookSiteRepository.class);

        System.out.println(beansOfType.toString());

        if (beansOfType != null && beansOfType.size() >= 1) {
            beansOfType.forEach((name, eachRepo)->{
                System.out.println(name + eachRepo.toString() + "\n");

            });

        }
    }
}