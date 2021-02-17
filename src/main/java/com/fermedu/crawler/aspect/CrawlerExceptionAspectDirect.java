package com.fermedu.crawler.aspect;

import com.guguskill.common.exception.GuException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @Program: book-crawler
 * @Create: 2021-02-03 14:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
//@Aspect
//@Component
public class CrawlerExceptionAspectDirect {

    /** only cut through the annotated methods
     * 连接点，对于方法的增强
     * processor包内任意类的任意方法
     * */
    @Pointcut(value = "execution( * com.fermedu.crawler.service.SpiderRunnerImpl(..))")
    public void pointcut() {
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void afterThrowing(GuException exception) throws Throwable{ // pjp 是注解的方法的封装
        System.out.println("\n\n\n\n");
        System.out.println(exception);
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable { // pjp 是注解的方法的封装
        Method method = ((MethodSignature) (pjp.getSignature())).getMethod();
        CatchCrawlerException annotation = method.getAnnotation(CatchCrawlerException.class);
        System.out.println(annotation);
        System.out.println(method);

        Object ret = pjp.proceed();
        return ret;
    }
}
