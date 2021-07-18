package com.github.conagreen.webserver;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class StaticFileController implements Controller {

    private static final String STATIC_FILE_DIR = "static";
    private static final Map<String, String> EXTENSION_MAP = new HashMap<>();

    static {
        EXTENSION_MAP.put("html", "text/html");
        EXTENSION_MAP.put("png", "image/png");
        EXTENSION_MAP.put("jpg", "image/jpg");
        EXTENSION_MAP.put("jpeg", "image/jpeg");
        EXTENSION_MAP.put("js", "application/javascript");
        EXTENSION_MAP.put("json", "application/json");
    }

    /*
    * 1. resources 아래에 있는 파일 읽기
    * 2. extract extension from uri - /index.html
    * 3. extension을 바탕으로 MIME 타입 결정
    * 4. response에 Content-Type 헤더세팅 & 읽기
    * */
    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        // 1. resources 아래에 있는 파일 읽기
        // 1. /.index.html
        final String filepath = STATIC_FILE_DIR + request.requestUri();
        final int lastIdx = request.requestUri().lastIndexOf(".");
        final String ext = request.requestUri().substring(lastIdx + 1);
        final String mime = EXTENSION_MAP.get(ext);
        response.addHeader("Content-Type", mime);

        final InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(filepath);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[65535];
        int readBytes;
        while ((readBytes = fileInputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, readBytes);
        }

        response.writeBody(baos.toByteArray());
    }
}
