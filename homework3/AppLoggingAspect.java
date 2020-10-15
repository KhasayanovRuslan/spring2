package com.geekbrains.geekmarketwinter.logging;

import com.geekbrains.geekmarketwinter.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppLoggingAspect {
    private static final Logger log = Logger.getLogger(AppLoggingAspect.class);

    @Before("execution(public * com.geekbrains.geekmarketwinter.services.UserServiceImpl.findByUserName(..))")
    public void beforeFindUserByUserName() {
        log.info("Найден пользователь по имени.");
    }

    @After("execution(public * com.geekbrains.geekmarketwinter.services.UserServiceImpl.loadUserByUsername(..))")
    public void afterLoadUserByUsername() {
        log.info("Загружен пользователь по имени.");
    }

    @After("execution(public * com.geekbrains.geekmarketwinter.services.ShoppingCartService.addToCart(..))")
    public void afterAddProductToCart() {
        log.info("Добавлен новый продукт в корзину.");
    }

    @Around("execution(public * com.geekbrains.geekmarketwinter.services.OrderService.makeOrder(..))")
    public void methodProfilingMakeOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("start profiling");
        long begin = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.info("Создан новый заказ.");
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
        System.out.println("end profiling");
    }

//    @Before("execution(public * com.geekbrains.geekmarketwinter.services.OrderService.saveOrder(..))")
//    public void beforeSaveOrder() {
//        log.info("Сохранен новый заказ.");
//    }
    @Around("execution(public * com.geekbrains.geekmarketwinter.services.OrderService.saveOrder(..))")
    public void methodProfilingSaveOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("start profiling");
        long begin = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.info("Сохранен новый заказ.");
        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
        System.out.println("end profiling");
    }
}
