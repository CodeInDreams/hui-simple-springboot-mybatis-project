package com.example.demo.aspect;

import com.example.demo.annotation.BusinessInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LogManager.getLogger(ControllerAspect.class);

    @Around("allMethodsPointcut()")
    public Object recordTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        BusinessInfo annotation = signature.getMethod().getAnnotation(BusinessInfo.class);
        String business = "";
        if (annotation != null) {
            business += "(Business name: " + annotation.name();
            if (!annotation.description().isEmpty())
                business += ", description: " + annotation.description();
            business += ")";
        }
        logger.info("Calling " + signature.getName() + business + ".");
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Method \"" + signature.getMethod() + "\" executed " + (endTime - startTime)/1000.0 + "s.");
        return returnValue;
    }

    @Pointcut("within(com.example.demo.controller.*)")
    public void allMethodsPointcut() {
    }
}
