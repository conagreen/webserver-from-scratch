package com.github.conagreen.webserver;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;
import com.github.conagreen.webserver.dispatcher.RequestDispatcher;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private final Socket socket;
    private static final RequestDispatcher DISPATCHER = new RequestDispatcher();

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (final InputStream in = socket.getInputStream(); final OutputStream out = socket.getOutputStream()) {
            final HttpRequest request = new HttpRequest(in);
            final HttpResponse response = new HttpResponse(out);
            RequestContextHolder.set(request, response);
            final Controller controller = DISPATCHER.dispatch(request);
            controller.process(request, response);
            RequestContextHolder.clearContext();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
