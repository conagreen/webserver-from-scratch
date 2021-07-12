package com.github.conagreen.controller;

import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

import java.io.IOException;

public interface Controller {
    void process(HttpRequest request, HttpResponse response) throws IOException;

}
