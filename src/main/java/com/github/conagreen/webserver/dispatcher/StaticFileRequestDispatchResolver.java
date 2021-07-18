package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.webserver.controller.StaticFileController;

import java.io.IOException;
import java.io.InputStream;

public class StaticFileRequestDispatchResolver implements RequestDispatchResolver {

    private static final String STATIC_FILE_DIR = "static";
    private static final Controller STATIC_FILE_CONTROLLER = new StaticFileController();

    @Override
    public boolean supports(HttpRequest request) {
        final String filepath = STATIC_FILE_DIR + request.requestUri();
        InputStream in = null;

        try {
            in = this.getClass().getClassLoader().getResourceAsStream(filepath);
            return in != null;
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public Controller dispatch(HttpRequest request) {
        return STATIC_FILE_CONTROLLER;
    }
}
