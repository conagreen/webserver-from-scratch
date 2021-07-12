package com.github.conagreen.webserver;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.controller.HelloController;
import com.github.conagreen.http.request.HttpMethod;
import com.github.conagreen.http.request.HttpRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestDispatcher {

    private static final Controller notFoundController = new NotFoundController();
    private static final Map<RequestKey, Controller> DISPATCHER_MAP = new HashMap<>();

    static {
        DISPATCHER_MAP.put(new RequestKey("/hello", HttpMethod.GET), new HelloController());
    }

    public Controller dispatch(HttpRequest request) {
        final Controller controller = DISPATCHER_MAP.get(new RequestKey(request.requestUri(), request.httpMethod()));
        if (controller != null) return controller;

        return notFoundController;
    }
}

class RequestKey {
    private final String uri;
    private final HttpMethod method;

    public RequestKey(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestKey that = (RequestKey) o;
        return Objects.equals(uri, that.uri) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }
}
