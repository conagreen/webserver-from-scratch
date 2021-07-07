package com.github.conagreen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

            // 응답
            final HttpResponse response = new HttpResponse(out);

            final DataOutputStream dos = new DataOutputStream(out);
            final String html = "" +
                    "<HTML>" +
                    "<HEAD><TITLE>핸드메이트 웹서버</TITLE></HEAD>" +
                    "<BODY><h1>제목이시다</h1><p>내용이시다</p></BODY>" +
                    "</HTML>";
            final int contentLength = html.getBytes().length;

            /*
            < 응답 문법 >

            A server sends response messages to the client,

            - a status line, consisting of the protocol version, a space, the response status code, another space, a possibly empty reason phrase, a carriage return, and a line feed (e.g. HTTP/1.1 200 OK);
            - zero or more response header fields, each consisting of the case-insensitive field name, a colon, optional leading whitespace, the field value, and optional trailing whitespace (e.g. Content-Type: text/html), and ending with a carriage return and a line feed;
            - an empty line, consisting of a carriage return and a line feed;
            - an optional message body.
            */

            // 1. 상태 라인 (status line)
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            // 2. 응답 필드 헤더
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + contentLength + "\r\n");
            dos.writeBytes("\r\n");
            // 3. 응답 바디 - optional
            dos.write(html.getBytes(), 0, contentLength);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
