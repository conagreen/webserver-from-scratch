package com.github.conagreen;

import java.io.OutputStream;

/*
* 응답 스펙
* 1. 상태 라인: HTTP/1.1 200 OK
* 2. 상태 헤더
* 3. 상태 바디
* */
public class HttpResponse {

    public HttpStatus status;

    // 생성자
    public HttpResponse(OutputStream out) {

    }

    public void addHeader(String key, String value) {

    }

    public void writeBody(byte[] body) {

    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
