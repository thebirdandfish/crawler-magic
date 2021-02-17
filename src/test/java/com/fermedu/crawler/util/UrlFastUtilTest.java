package com.fermedu.crawler.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: book-crawler
 * @Create: 2020-12-12 00:34
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class UrlFastUtilTest {
    @Test
    void complete() {
        String url1 = "http://m.31xs.com/19/19265/";
        String url2 = "http://m.31xs.com/19/19265/index.htm";
        String url3 = "index.htm";
        String url4 = "/index.htm";

        String url5 = "http://m.31xs.com";
        String url6 = "http://m.31xs.com/";
        String url7 = "http://m.31xs.com/#";
        String url8 = "m.31xs.com";

        System.out.println(UrlFastUtil.complete(url1, url2));
        System.out.println(UrlFastUtil.complete(url1, url3));
        System.out.println(UrlFastUtil.complete(url1, url4));

        System.out.println(UrlFastUtil.setTimestampParamToGetUrl(url5));
        System.out.println(UrlFastUtil.setTimestampParamToGetUrl(url6));
        System.out.println(UrlFastUtil.setTimestampParamToGetUrl(url7));
        System.out.println(UrlFastUtil.setTimestampParamToGetUrl(url8));
    }

    @Test
    void name() {
        String url1 = "https://bbs.csdn.net/topics/30416489";
        String url2 = "http://bbs.csdn.net:81/topics/30416489";
        boolean b = UrlFastUtil.sameUrl(url1, url2);
        System.out.println(b);
    }
}