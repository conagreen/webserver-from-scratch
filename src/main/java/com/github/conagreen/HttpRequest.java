package com.github.conagreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * HTTP Request
 * 1. 요청라인
 * 2. 헤더
 * 3. 바디(optional)
 * */
public class HttpRequest {

    private final RequestLine requestLine;
    private final Map<String, String> headerMap = new HashMap<>();
    // 생성자
    public HttpRequest(InputStream in) {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            // 요청라인
            this.requestLine = new RequestLine(br.readLine());
            // 헤더 파싱
            String line;
            while (!(line = br.readLine()).equals("")) {
//                System.out.println(line);
                final String[] keyAndValue = line.split(":", 2);
                headerMap.put(keyAndValue[0].trim(), keyAndValue[1].trim());
            }
        } catch (IOException e) {
            throw new IllegalStateException("IO 문제 발생");
        }
    }

    public String getHeader (String headerName) {
        return this.headerMap.get(headerName);
    }

    public HttpMethod httpMethod() {
        return requestLine.httpMethod();
    }

    public String requestUri() {
        return requestLine.requestUri();
    }

    // name=value1&number=value2
    public String getQueryString() {
        return requestLine.getQueryString();
    }

    public String getParameter(String key) {
        return requestLine.getParameter(key);
    }
}