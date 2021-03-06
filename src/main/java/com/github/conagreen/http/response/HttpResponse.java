package com.github.conagreen.http.response;

import com.github.conagreen.http.Cookie;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 응답 스펙
* 1. 상태 라인: HTTP/1.1 200 OK
* 2. 상태 헤더
* 3. 상태 바디
* */
public class HttpResponse {

    private final DataOutputStream dos;
    public HttpStatus status = HttpStatus.OK;
    private final Map<String, String> responseHeaderMap = new HashMap<>();
    private final List<Cookie> cookies = new ArrayList<>();

    // 생성자
    public HttpResponse(OutputStream out) {
        this.dos = new DataOutputStream(out);
    }

    public void addHeader(String key, String value) {
        responseHeaderMap.put(key, value);
    }

    public void writeBody(byte[] body) throws IOException {
        // 응답 라인 (status line)
        dos.writeBytes(makeStatusLine());

        // 응답 헤더들
        for (Map.Entry<String, String> entry : responseHeaderMap.entrySet()) {
            dos.writeBytes(String.format("%s: %s\r\n", entry.getKey(), entry.getValue()));
        }
        dos.writeBytes("Content-Length: " + body.length + "\r\n");

        if (!cookies.isEmpty()) {
            for (Cookie cookie : cookies) {
                dos.writeBytes("Set-Cookie: " + cookie.serialize() + "\r\n");
            }
        }

        dos.writeBytes("\r\n");

        // 바디
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private String makeStatusLine() {
        return String.format("HTTP/1.1 %d %s\r\n", status.getStatusCode(), status.getReasonPhrase());
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /*
    * HTTP/1.1 302 Found
    * Location: url
    * */
    public void sendRedirect(String url) throws IOException {
        setStatus(HttpStatus.FOUND);
        dos.writeBytes(makeStatusLine());
        if (!cookies.isEmpty()) {
            for (Cookie cookie : cookies) {
                dos.writeBytes("Set-Cookie: " + cookie.serialize() + "\r\n");
            }
        }
        dos.writeBytes("Location: " + url + "\r\n");
        dos.flush();
    }

    // Set-Cookie: KEY1=VALUE1; KEY2=VALUE2
    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }
}
