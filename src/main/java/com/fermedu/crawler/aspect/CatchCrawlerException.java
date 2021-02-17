package com.fermedu.crawler.aspect;

import java.lang.annotation.*;

/**
 * @Program: book-crawler
 * @Create: 2021-02-03 13:58
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Retention(RetentionPolicy.RUNTIME) // 方法上面使用 ,source 仅出现在java代码中， class说明会在class字节码中出现（如lombok）
@Target({ElementType.METHOD}) // 运行时生效
@Documented
public @interface CatchCrawlerException {
    Class<? extends Throwable>[] clz() default {};
}
