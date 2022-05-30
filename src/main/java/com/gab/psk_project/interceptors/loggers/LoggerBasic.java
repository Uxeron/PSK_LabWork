package com.gab.psk_project.interceptors.loggers;

import javax.enterprise.inject.Alternative;
import javax.interceptor.InvocationContext;

@Alternative
public class LoggerBasic implements Logger {
    public void logMethodInvocation(InvocationContext context) {
        System.out.println("Called method: " + context.getMethod().getName());
    }
}
