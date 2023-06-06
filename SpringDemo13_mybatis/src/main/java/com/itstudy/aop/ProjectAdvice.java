package com.itstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProjectAdvice {

    //切入点
    @Pointcut("execution(* com.itstudy.service.*Service.*(..))")
    public void pt(){}
    //通知

    @Around("pt()")
    public Object findByIdAdvice(ProceedingJoinPoint pjp) throws Throwable {
        //添加签名信息, 记录运行过程
        Signature signature = pjp.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        //接口/类名
        //System.out.println(signature.getDeclaringTypeName());
        //增加功能的方法
        //System.out.println(signature.getName());

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pjp.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println("万次执行" + className + "." + methodName + "--->" + (end-start) + "ms");
        return pjp.proceed();
    }
}
