package com.nhnacademy.edu.springframework.aspect;

import com.nhnacademy.edu.springframework.annotation.ElapsedTimeLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(elapsedTimeLog)")
    public Object loggingExecutionTime(ProceedingJoinPoint pjp, ElapsedTimeLog elapsedTimeLog) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object rt = null;
        try {
            rt = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            long elapsedTime = System.currentTimeMillis() - beginTime;
            log.info("[" + pjp.getTarget().getClass().getSimpleName() + "].[" + pjp.getSignature().getName() + "][" + elapsedTime + "]ms");

            return rt;
        }
    }
}
