package com.itstudy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
    @Pointcut("execution(* com.itstudy.dao.BookDao.*(..))")
    private void pt() {
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
    /*public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice...");
        //表示对原始操作的调用, 会强制抛异常
        //环绕通知必须依赖形参ProceedingJoinPoint才能对原始方法调用
        //必须抛出异常Throwable, 因为原方法中说不定会有异常
        Object proceed = pjp.proceed();
        System.out.println("around after advice...");
        return proceed;
    }*/

    //@Around("pt()")
    /*public Object aroundSelect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice...");
        //表示对原始操作的调用, 会强制抛异常
        Object proceed = pjp.proceed();
        System.out.println("around after advice...");
        return proceed;
    }*/

    //原始方法如果有返回值, 就装到ret这个变量里
    //@AfterReturning(value = "pt()", returning = "ret")
    public void afterReturning(Object ret) {
        System.out.println("afterReturning advice..." + ret);
    }

    //@AfterThrowing(value = "pt()", throwing = "throwable")
    public void afterThrowing(Throwable throwable) {
        System.out.println("afterThrowing advice..." + throwable);
    }

    //@Around("pt()")
    public Object countTime(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        args[0] = "alen";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            try {
                pjp.proceed(args);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("运行了" + (end - start) + "ms");
        return pjp.proceed(args);
    }

    //@Before("pt()")
    public void beforeFindName(JoinPoint jp) {
        //获取参数
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("before advice findName...");
    }


}
