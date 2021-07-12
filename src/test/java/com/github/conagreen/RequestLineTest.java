package com.github.conagreen;

import com.github.conagreen.http.request.HttpMethod;
import com.github.conagreen.http.request.RequestLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("요청라인 객체 테스트")
class RequestLineTest {

    @DisplayName("요청라인이 GET /path?param=1 HTTP/1.1 이다.")
    @Test
    void testRequestLine() {
        final String rawRequestLine = "GET /path?param=1 HTTP/1.1";
        final RequestLine requestLine = new RequestLine(rawRequestLine);

        assertEquals("/path", requestLine.requestUri());
        assertEquals(HttpMethod.GET, requestLine.httpMethod());
        assertEquals("1", requestLine.getParameter("param"));
        assertEquals("param=1", requestLine.getQueryString());
    }

    @DisplayName("QueryString이 비어있는 경우 null")
    @Test
    void testQueryStringIsNull() {
        final String rawRequestLine = "GET /path HTTP/1.1";

        final RequestLine requestLine = new RequestLine(rawRequestLine);
        assertNull(requestLine.getQueryString());
    }
}