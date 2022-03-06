package com.github.conagreen.webserver;

public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> REQUEST_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static RequestContext get() {
        return REQUEST_CONTEXT_THREAD_LOCAL.get();
    }

    public static void setRequestContext(RequestContext context) {
        REQUEST_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void clearContext() {
        REQUEST_CONTEXT_THREAD_LOCAL.remove();
    }
}
