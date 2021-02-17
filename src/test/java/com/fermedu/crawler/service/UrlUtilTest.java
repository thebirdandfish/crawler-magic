package com.fermedu.crawler.service;

import com.guguskill.common.util.UrlUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 00:06
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class UrlUtilTest {

    @Test
    void getUrlSuffix() {
        String url1 = "https://www.huya.com/cxldb";
        String url2 = "https://www.google.com/search?q=Suffix&oq=Suffix&aqs=chrome..69i57.377j0j7&sourceid=chrome&ie=UTF-8";
        System.out.println(UrlUtil.getUrlSuffix(url1));
        System.out.println(UrlUtil.getUrlSuffix(url2));
    }
}