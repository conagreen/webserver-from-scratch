package com.github.conagreen.controller;

import com.github.conagreen.http.Cookie;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;
import com.github.conagreen.http.response.HttpStatus;

import java.io.IOException;

public class HelloController implements Controller {

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        final String html = "{" + "\r\n" + "\"message\": \"Welcome to Web Server!\""
                + "\r\n" + "\"username\": \"" + request.getSession().getAttribute("username") + "\r\n" + "\"}";
        response.setStatus(HttpStatus.OK);
        response.addHeader("Content-Type", "application/json;charset=utf-8");
        final Cookie welcomeCookie = new Cookie("welcome_cookie", "yummy");
        welcomeCookie.setMaxAge(120);
        welcomeCookie.setHttpOnly(true);
        response.addCookie(welcomeCookie);
        response.writeBody(html.getBytes());
    }
}
