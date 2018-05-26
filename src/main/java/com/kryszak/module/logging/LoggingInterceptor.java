package com.kryszak.module.logging;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class LoggingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String clazzName = methodInvocation.getMethod().getDeclaringClass().getSimpleName();
        String methodName = methodInvocation.getMethod().getName();
        LogLevel level = methodInvocation.getMethod().getAnnotation(LogMethodCall.class).level();

        String classMethod = String.format("%s.%s", clazzName, methodName);

        Object[] args = methodInvocation.getArguments();

        String entryLog = ">> {} called. ";

        if (LogLevel.INFO.equals(level)) {
            log.info(entryLog, classMethod, args);
        } else if (LogLevel.DEBUG.equals(level)) {
            entryLog += "Parameters: {}";
            log.debug(entryLog, classMethod, args);
        }

        Object result = methodInvocation.proceed();

        String endingLog = "<< {} finished.";

        if (LogLevel.INFO.equals(level)) {
            log.info(endingLog, methodName);
        } else if (LogLevel.DEBUG.equals(level)) {
            log.debug(endingLog, methodName);
        }

        return result;
    }
}
