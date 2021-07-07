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

    private String requestLine;
    private String method;
    private String uri;
    private Map<String, String> headerMap = new HashMap<>();

    // 생성자
    public HttpRequest(InputStream in) {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // 요청 라인 파싱
            this.requestLine = br.readLine();
            final String[] tokens = this.requestLine.split(" ");
            this.method = tokens[0];
            this.uri =  tokens[1];

            // 헤더 파싱
            String line;
            while (!(line = br.readLine()).equals("")) {
//                System.out.println(line);
                final String[] keyAndValue = line.split(":", 2);
                headerMap.put(keyAndValue[0].trim(), keyAndValue[1].trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHeader (String headerName) {
        return this.headerMap.get(headerName);
    }

    public HttpMethod httpMethod() {
        return HttpMethod.valueOf(method);
    }

    public String requestUri() {
        return uri;
    }

    public String getQueryString() {

        return null;
    }

    public String getParameter(String key) {

        return null;
    }
}