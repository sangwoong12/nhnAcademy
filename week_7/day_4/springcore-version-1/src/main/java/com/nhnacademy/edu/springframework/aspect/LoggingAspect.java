package com.nhnacademy.edu.springframework.aspect;

import com.nhnacademy.edu.springframework.annotation.ElapsedTimeLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

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
