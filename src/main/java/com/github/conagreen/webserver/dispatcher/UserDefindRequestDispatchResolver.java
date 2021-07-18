package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.controller.HelloController;
import com.github.conagreen.http.request.HttpMethod;
import com.github.conagreen.http.request.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 사용자가 정의한 컨트롤러를 dispatching 해주는 resolver
 */
public class UserDefindRequestDispatchResolver implements RequestDispatchResolver{

    private static final Map<RequestKey, Controller> DISPATCHER_MAP = new HashMap<>();

    static {
        DISPATCHER_MAP.put(new RequestKey("/hello", HttpMethod.GET), new HelloController());
    }

    @Override
    public boolean supports(HttpRequest request) {
        return DISPATCHER_MAP.get(new RequestKey(request.requestUri(), request.httpMethod())) != null;
    }

    @Override
    public Controller dispatch(HttpRequest request) {
        return DISPATCHER_MAP.get(new RequestKey(request.requestUri(), request.httpMethod()));
    }
}
