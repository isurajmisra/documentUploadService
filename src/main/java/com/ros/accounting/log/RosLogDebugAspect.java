package com.ros.accounting.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RosLogDebugAspect {
    @Around("@annotation(RosLogDebug)")
    public Object logDebug(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = null;
        String methodName = null;
        try {
            logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
            methodName = joinPoint.getSignature().getName();
            logger.debug("{} args: {}", methodName, joinPoint.getArgs());
        } catch (Exception e) {

        }

        Object proceed = joinPoint.proceed();

        try {

            if (logger.isDebugEnabled()) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                String returnType = signature.getReturnType().getName();
                if (returnType.equals("void")) {
                    logger.debug("{} returned {}", methodName, returnType);
                } else {
                    logger.debug("{} returned {}: {}", methodName, returnType, proceed);
                }
            }

        } catch (Exception e) {

        }

        return proceed;
    }

}
