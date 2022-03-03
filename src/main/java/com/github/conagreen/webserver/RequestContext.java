package com.github.conagreen.webserver;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

public class RequestContext {

    private HttpRequest request;
    private HttpResponse response;

    public RequestContext(HttpRequest request, HttpResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }
}
