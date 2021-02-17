package com.fermedu.crawler.util;

import com.guguskill.common.util.UrlUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: book-crawler
 * @Create: 2020-12-11 17:25
 * @Author: JustThink
 * @Description: url 类似于<a href="/5/5021/" class="am-list-item-hd"><i class="am-icon-book am-icon-fw"></i>绝世邪神</a>
 * 补全完整url
 * @Include:
 **/
public class UrlFastUtil {

    /***
    * @Description sameUrl String url1 = "https://bbs.csdn.net/topics/30416489";
     *         String url2 = "http://bbs.csdn.net:81/topics/30416489";
     *         是一样的
    * @Params * @param url1
* @param url2
    * @Return boolean
    **/
    public static boolean sameUrl(String url1, String url2) {
        if (UrlUtil.getHost(url1).equals(UrlUtil.getHost(url2))) {

        } else {
            return false;
        }
        if (UrlUtil.getUrlSuffix(url1).equals(UrlUtil.getUrlSuffix(url2))) {

        } else {
            return false;
        }

        return true;
    }

    /***
    * @Description complete
    * @Params * @param urlContainingPrefix http://www.example.com/123/123
    * @param potentiallyShortUrl /123/123
    * @Return java.lang.String
    **/
    public static String complete(String urlContainingPrefix, String potentiallyShortUrl) {
        if (UrlUtil.isUrl(potentiallyShortUrl)) {
            /** 是url */
            return potentiallyShortUrl;
        } else {

        }
        /** 短url */
        String urlPrefix = UrlUtil.getUrlPrefix(urlContainingPrefix);
        String processedUrl = urlPrefix;
        if (urlPrefix.endsWith("/")) {
            int length = urlPrefix.length();
            processedUrl = urlPrefix.substring(0, length - 1);
        }

        String processedShortUrl = potentiallyShortUrl;
        if (potentiallyShortUrl.startsWith("/")) {
        } else {
            processedShortUrl = "/".concat(potentiallyShortUrl);
        }

        return processedUrl + processedShortUrl;
    }

    /***
    * @Description convertDomainToUrl
    * @Params * @param domain www.example.com
    * @Return java.lang.String http://www.example.com
    **/
    public static String convertDomainToUrl(String domain) {
        if (domain.startsWith("http")) {
            return domain;
        } else {
            return "http://" + domain+"/";
        }
    }

    /** 在url后面加时间戳参数，防止缓存，防止认定为相同url */
    public static String setTimestampParamToGetUrl(String url) {
        /** 将url补全 */
        String fullUrl = UrlFastUtil.convertDomainToUrl(url);

        String timeString = String.valueOf(System.currentTimeMillis());

        Map<String, String> timestampMap = new HashMap<>();
        timestampMap.put("timestamp", timeString);

        return UrlUtil.setParamForGetUrl(fullUrl, timestampMap);
    }
}
