package com.gab.psk_project.interceptors;

import com.gab.psk_project.interceptors.loggers.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable {
    @Inject
    Logger logger;

    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        logger.logMethodInvocation(context);
        return context.proceed();
    }
}
