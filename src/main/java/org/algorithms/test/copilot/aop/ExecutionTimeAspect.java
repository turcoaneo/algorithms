package org.algorithms.test.copilot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("@annotation(org.algorithms.test.copilot.aop.TrackExecutionTime)") // Intercept methods with @TrackExecutionTime
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // Start timer

        Object result = joinPoint.proceed(); // Execute method

        long elapsedTime = System.currentTimeMillis() - start; // Calculate time
        System.out.println(joinPoint.getSignature() + " executed in " + elapsedTime + "ms");

        return result;
    }
}