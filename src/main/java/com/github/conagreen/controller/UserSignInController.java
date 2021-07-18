package com.github.conagreen.controller;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

import java.io.IOException;

public class UserSignInController implements Controller{

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println(request.getHeader("Cookie"));
        if ("cona".equals(username) && "1234".equals(password)) {
            // 로그인
        }
        response.sendRedirect("/index.html");
    }
}
