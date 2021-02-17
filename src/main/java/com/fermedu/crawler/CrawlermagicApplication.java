package com.fermedu.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.guguskill.*","com.fermedu.*"})
@EnableScheduling // 定时任务发现注解@Scheduled的任务并后台执行。
@EnableAspectJAutoProxy(exposeProxy = true)
// @EnableCaching
public class CrawlermagicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlermagicApplication.class, args);
	}
}
