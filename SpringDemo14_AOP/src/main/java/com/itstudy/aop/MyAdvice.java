package com.itstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
 * 通知类
 *
 * @Component受到spring控制
 * @Aspect定义这个为通知类, 里面的是切入点和通知
 * */
@Component
@Aspect
public class MyAdvice {
    /*
     * 描述切入点@Pointcut("execution(void com.itstudy.dao.BookDao.update())")
     * @Point("execution(切入点方法返回类型 切入点包名.类名.方法名)")
     * */
    @Pointcut("execution(void com.itstudy.dao.BookDao.update())")
    private void pt() {
    }

    @Pointcut("execution(int com.itstudy.dao.BookDao.select())")
    private void pt2() {
    }

    /*
     * 通知(共性功能)
     *
     * 切面描述@Before("pt()")
     * 在切入点执行之前执行下面这个方法
     * */
    /*@Before("pt()")
    public void Method() {
        System.out.println(System.currentTimeMillis());
    }*/

    //@Before("pt()")
    public void before() {
        System.out.println("before advice...");
    }

    //@After("pt()")
    public void after() {
        System.out.println("after advice...");
    }

    //@Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice...");
        //表示对原始操作的调用, 会强制抛异常
        //环绕通知必须依赖形参ProceedingJoinPoint才能对原始方法调用
        //必须抛出异常Throwable, 因为原方法中说不定会有异常
        Object proceed = pjp.proceed();
        System.out.println("around after advice...");
        return proceed;
    }

    @Around("pt2()")
    public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice...");
        //表示对原始操作的调用, 会强制抛异常
        Object proceed = pjp.proceed();
        System.out.println("around after advice...");
        return proceed;
    }

    //@AfterReturning("pt()")
    public void afterReturning() {
        System.out.println("afterReturning advice...");
    }

    //@AfterThrowing("pt2()")
    public void afterThrowing() {
        System.out.println("afterThrowing advice...");
    }

    @Around("pt()")
    public Object countTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("运行了" + (end - start) + "ms");
        return proceed;
    }
}
