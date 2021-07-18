package com.github.conagreen.webserver.controller;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;
import com.github.conagreen.http.response.HttpStatus;

import java.io.IOException;

public class NotFoundController implements Controller {

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        final String html = "" +
                "<html>" +
                "<head>" +
                "   <title>Not Found</title>" +
                "</head>" +
                "<body>" +
                "   <h1>페이지가 존재하지 않습니다.</h1>" +
                "   <p>다시 확인해주세요.</p>" +
                "</body>" +
                "</html>";

        response.setStatus(HttpStatus.NOT_FOUND);
        response.addHeader("X-WRITING-WEB-SERVER-FROM-SCRATCH", "nothing");
        response.addHeader("Content-Type", "text/html;charset=utf-8");
        response.writeBody(html.getBytes());

    }
}
