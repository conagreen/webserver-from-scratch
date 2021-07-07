package com.github.conagreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * HTTP Request
 * 1. 요청라인
 * 2. 헤더
 * 3. 바디(optional)
 * */
public class HttpRequest {

    // 생성자
    public HttpRequest(InputStream in) {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while (!(line = br.readLine()).equals("")) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHeader (String headerName) {

        return null;
    }

    public HttpMethod httpMethod() {

        return null;
    }

    public String requestUri() {

        return null;
    }

    public String getQueryString() {

        return null;
    }

    public String getParameter(String key) {

        return null;
    }
}