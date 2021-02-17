package com.fermedu.crawler.aspect;

import com.guguskill.common.exception.GuException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Program: book-crawler
 * @Create: 2021-02-03 14:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Aspect
@Component
public class CrawlerExceptionAspect {

    /** only cut through the annotated methods
     * 连接点，对于方法的增强 */

    @AfterThrowing(pointcut = "@annotation(anno)", throwing = "exception")
    public void afterThrowing(GuException exception, CatchCrawlerException anno) throws Throwable{
        System.out.println("\n\n\n\n");
        System.out.println(exception);
    }

    @Around("@annotation(anno)")
    public Object around(ProceedingJoinPoint pjp,CatchCrawlerException anno) throws Throwable { // pjp 是注解的方法的封装
        Method method = ((MethodSignature) (pjp.getSignature())).getMethod();
        CatchCrawlerException annotation = method.getAnnotation(CatchCrawlerException.class);
        System.out.println(annotation);
        System.out.println(method);

        Object ret = pjp.proceed();
        return ret;
    }
}
