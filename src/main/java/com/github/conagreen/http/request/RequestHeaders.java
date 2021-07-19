package com.github.conagreen.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

    private final Map<String, String> headerMap = new HashMap<>();

    // 생성자
    public RequestHeaders(final BufferedReader br) {
        try {
            String line;

            while (!(line = br.readLine()).equals("")) {
                final String[] headerAndValue = line.split(":", 2);
                headerMap.put(headerAndValue[0].trim(), headerAndValue[1].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHeader(String headerName) {
        return headerMap.get(headerName);
    }
}
