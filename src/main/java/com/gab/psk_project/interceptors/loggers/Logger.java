package com.gab.psk_project.interceptors.loggers;

import javax.interceptor.InvocationContext;
import java.io.Serializable;

public interface Logger extends Serializable {
    void logMethodInvocation(InvocationContext context);
}
