package com.github.conagreen.controller;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;
import com.github.conagreen.http.session.HttpSession;

import java.io.IOException;

public class UserSignInController implements Controller{

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        if ("conagreen".equals(username) && "1234".equals(password)) {
            final HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/hello");
        } else {
            response.sendRedirect("/index.html");
        }
    }
}
