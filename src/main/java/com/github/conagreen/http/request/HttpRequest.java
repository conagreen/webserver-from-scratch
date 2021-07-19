package com.github.conagreen.http.request;

import com.github.conagreen.http.Cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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
    private final RequestHeaders headers;
    private final Map<String, String> parameterMap = new HashMap<>();

    // 생성자
    public HttpRequest(InputStream in) {
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));
            this.requestLine = new RequestLine(br.readLine());
            this.headers = new RequestHeaders(br);

            final String contentType = headers.getHeader("Content-Type");
            final String lengthHeaderValue = headers.getHeader("Content-Length");
            if (lengthHeaderValue != null) {
                if ("application/x-www-form-urlencoded".equals(contentType)) {
                    final int contentLength = Integer.parseInt(lengthHeaderValue);
                    char[] body = new char[contentLength];
                    br.read(body);
                    final String rawFormData = String.copyValueOf(body);
                    Arrays.stream(rawFormData.split("&"))
                            .forEach(kv -> {
                                final String[] keyAndValue = kv.split("=");
                                parameterMap.put(keyAndValue[0].trim(), keyAndValue[1].trim());
                            });
                }
            }


        } catch (IOException e) {
            throw new IllegalStateException("IO 문제 발생");
        }
    }

    public String getHeader (String headerName) {
        return this.headers.getHeader(headerName);
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
        final String param = requestLine.getParameter(key);
        if (param != null) {
            return param;
        }
        return parameterMap.get(key);
    }

    // KEY1=VALUE1;
    // KEY2=VALUE2;
    public Cookie[] getCookies() {
        return null;
    }
}