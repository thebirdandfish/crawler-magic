package com.fermedu.crawler.config;

import com.guguskill.util.IpUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: book-crawler
 * @Create: 2021-02-06 17:01
 * @Author: JustThink
 * @Description: 本机ip和端口
 * @Include:
 **/
@Configuration
@Slf4j
public class LocalConfig {

    @Getter
    private final String ipAddress = IpUtil.getLocalHostAddress();

    @Getter
    @Value("${server.port}")
    private int port;
}
