package com.itstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAdvice {
    @Pointcut("execution(boolean com.itstudy.dao.BookDao.*(..))")
    private void pt() {
    }

    @Around("pt()")
    public Object testPassword(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            //判断参数是不是字符串, trim()去掉最后一个空格
            if (args[i].getClass().equals(String.class)) {
                args[i] = args[i].toString().trim();
            }
        }
        return pjp.proceed(args);
    }
}
