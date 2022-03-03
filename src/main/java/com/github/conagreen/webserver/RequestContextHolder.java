package com.github.conagreen.webserver;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> REQUEST_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    public static RequestContext get() {
        return REQUEST_CONTEXT_THREAD_LOCAL.get();
    }

    public static void set(HttpRequest request, HttpResponse response) {
        REQUEST_CONTEXT_THREAD_LOCAL.set(new RequestContext(request, response));
    }
}
