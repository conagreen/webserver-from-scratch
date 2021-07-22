package com.github.conagreen.controller;

import com.github.conagreen.http.Cookie;
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
        final Cookie c1 = new Cookie("yummy_cookie", "choco");
        c1.setMaxAge(86400);
        c1.setHttpOnly(true);
        final Cookie c2 = new Cookie("cona_cookie", "green");
        response.addCookie(c1);
        response.addCookie(c2);
        response.writeBody(html.getBytes());
        System.out.println("helloController: " + Thread.currentThread().getName() + "; path=" + request.requestUri());
    }
}
