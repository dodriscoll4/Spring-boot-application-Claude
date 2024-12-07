package com.example.ClaudeSpringApplication.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ServiceLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    // Pointcut for all service methods
    @Pointcut("within(com.example.ClaudeSpringApplication.services.*)")
    public void serviceMethods() {}

    // Specific pointcuts for critical operations
    @Pointcut("execution(* com.example.ClaudeSpringApplication.services.PetServiceImpl.createPet(..))")
    public void petCreation() {}

    @Pointcut("execution(* com.example.ClaudeSpringApplication.services.PetServiceImpl.deletePet*(..))")
    public void petDeletion() {}

    @Pointcut("execution(* com.example.ClaudeSpringApplication.services.HouseholdServiceImpl.createHousehold(..))")
    public void householdCreation() {}

    @Pointcut("execution(* com.example.ClaudeSpringApplication.services.HouseholdServiceImpl.deleteHousehold*(..))")
    public void householdDeletion() {}

    // Around advice for measuring execution time of critical operations
    @Around("petCreation() || petDeletion() || householdCreation() || householdDeletion()")
    public Object logCriticalOperationExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        String methodName = joinPoint.getSignature().getName();

        logger.info("Starting critical operation: {}", methodName);
        stopWatch.start();

        try {
            Object result = joinPoint.proceed();
            stopWatch.stop();
            logger.info("Completed {} in {} ms", methodName, stopWatch.getTotalTimeMillis());
            return result;
        } catch (Exception e) {
            logger.error("Error in {}: {}", methodName, e.getMessage());
            throw e;
        }
    }

    // Before advice for logging service method entry
    @Before("serviceMethods()")
    public void logMethodEntry(org.aspectj.lang.JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.debug("Entering method: {} with arguments: {}", methodName, args);
    }

    // After advice for logging service method exit
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logMethodExit(org.aspectj.lang.JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.debug("Exiting method: {} with result: {}", methodName, result);
    }

    // Exception handling advice
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logException(org.aspectj.lang.JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Exception in {}: {} - {}", methodName,
                exception.getClass().getSimpleName(),
                exception.getMessage());
    }

    // Specific advice for statistics operations
    @Around("execution(* com.example.ClaudeSpringApplication.services.*ServiceImpl.get*Statistics(..))")
    public Object logStatisticsOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Calculating statistics for: {}", methodName);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        logger.info("Statistics calculation completed in {} ms", stopWatch.getTotalTimeMillis());

        return result;
    }
}