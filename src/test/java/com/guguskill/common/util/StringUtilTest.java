package com.guguskill.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: book-crawler
 * @Create: 2020-12-30 21:21
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
class StringUtilTest {

    @Test
    void replaceRegexWith() {
        String rawString = "【领现金红包】看书即可领现金！关注微信.公众号【书友大本营】，现金/点币等你拿！";
        String regex = "\\u3010\\u9886\\u73b0\\u91d1\\u7ea2\\u5305.{10,500}\\u5e01\\u7b49\\u4f60\\u62ff\\uff01";
        String into = "";

        String left = StringUtil.replaceRegexWith(rawString, regex, into);

        System.out.println(left);
    }
}