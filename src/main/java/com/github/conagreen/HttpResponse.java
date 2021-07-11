package com.github.conagreen;

import java.io.*;
import java.util.HashMap;
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
        dos.writeBytes("\r\n");

        // 바디
        dos.write(body, 0, body.length);
        dos.flush();
    }

    public void writeImage(File file) throws IOException {
        // 응답 라인 : HTTP/1.1 200 OK
        setStatus(HttpStatus.OK);
        dos.writeBytes(makeStatusLine());

        // 헤더
        // 참고: mozilla content type for jpg image [google]
        // 0. 구현
        // 1. 왜 Content-Type을 세팅해주어야 하는가?
        // 2. 왜 Content-Length를 세팅해주어야 하는가?
        dos.writeBytes("Content-Type: image/jpg\r\n");
        dos.writeBytes("Content-Length: " + file.length() + "\r\n");
        dos.writeBytes("\r\n");

        // 바디
        FileInputStream fis = new FileInputStream(file);
        final byte[] buffer = new byte[4096];
        int result = 0;

        while ((result = fis.read(buffer)) != -1) {
            dos.write(buffer);
        }

        dos.flush();
    }

    private String makeStatusLine() {
        return String.format("HTTP/1.1 %d %s\r\n", status.getStatusCode(), status.getReasonPhrase());
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
