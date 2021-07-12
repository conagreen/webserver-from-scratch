package com.github.conagreen.controller;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;
import com.github.conagreen.http.response.HttpStatus;

import java.io.IOException;

public class HelloController implements Controller {

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        final String html = "{\"message\": \"Hello Web Server.\"}";
        response.setStatus(HttpStatus.OK);
        response.addHeader("Content-Type", "application/json;charset=utf-8");
        response.writeBody(html.getBytes());
    }
}
