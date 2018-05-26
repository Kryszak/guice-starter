package com.kryszak.module.logging;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TimingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String clazzName = methodInvocation.getClass().getSimpleName();
        String methodName = methodInvocation.getMethod().getName();
        LogLevel level = methodInvocation.getMethod().getAnnotation(TimeMethodCall.class).level();

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

        String endingLog = "<< {} finished in {}ms.";

        stopwatch.stop();

        if (LogLevel.INFO.equals(level)) {
            log.info(endingLog, methodName, String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
        } else if (LogLevel.DEBUG.equals(level)) {
            log.debug(endingLog, methodName, String.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS)));
        }

        return result;
    }
}
