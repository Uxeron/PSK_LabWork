package com.gab.psk_project.interceptors.loggers;

import javax.enterprise.inject.Alternative;
import javax.interceptor.InvocationContext;

@Alternative
public class LoggerDetailed implements Logger {
    public void logMethodInvocation(InvocationContext context) {
        System.out.println("Called method: " + context.getMethod().getName());
        System.out.println("With parameters:");
        for (Object parameter: context.getParameters()) {
            System.out.println(parameter);
        }
        System.out.println("--------------");
    }
}
