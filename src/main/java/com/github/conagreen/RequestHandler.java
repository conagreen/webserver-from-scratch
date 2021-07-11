package com.github.conagreen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {

    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (final InputStream in = socket.getInputStream(); final OutputStream out = socket.getOutputStream()) {
            //요청
            final HttpRequest request = new HttpRequest(in);
//            System.out.println("request.httpMethod() = " + request.httpMethod());
//            System.out.println("request.requestUri() = " + request.requestUri());
//            System.out.println("request.getHeader(\"Host\") = " + request.getHeader("Host"));
//            System.out.println("request.getHeader(\"Accept\") = " + request.getHeader("Accept"));
//            System.out.println("request.getHeader(\"User-Agent\") = " + request.getHeader("User-Agent"));
//            System.out.println("request.getQueryString() = " + request.getQueryString());
//            System.out.println("request.getParameter(\"name\") = " + request.getParameter("name"));

            // 응답
            final HttpResponse response = new HttpResponse(out);

            final DataOutputStream dos = new DataOutputStream(out);
            final String html = "" +
                    "<HTML>" +
                    "<HEAD><TITLE>핸드메이트 웹서버</TITLE></HEAD>" +
                    "<BODY><h1>제목이시다</h1><p>내용이시다</p></BODY>" +
                    "</HTML>";
            final int contentLength = html.getBytes().length;

            // 1. 상태 라인 (status line)
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            // 2. 응답 필드 헤더
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + contentLength + "\r\n");
            dos.writeBytes("\r\n");
            // 3. 응답 바디 - optional
            dos.write(html.getBytes(), 0, contentLength);
            dos.flush();

            response.setStatus(HttpStatus.OK);
            response.addHeader("Content-Type", "text/html;charset=utf-8");
            response.addHeader("Content-Length", String.valueOf(contentLength));
            response.writeBody(html.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
