package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(* org.example.service..*.*(..))")
    public void service(){}


    @Before("service()")
    public void beforeService(JoinPoint joinPoint){
        System.out.println("Before Service");
    }


    @After("service()")
    public void afterService(){
        System.out.println("After Service");
    }

   @Around("service()")
   public Object aroundService(ProceedingJoinPoint joinPoint){
        Object object=null;
        try{
            System.out.println("Before Around Service");
            object=joinPoint.proceed();
            System.out.println("After Around Service. "+object);
        }catch(Throwable throwable){
            throwable.printStackTrace();
        }
        return object;
    }
}