package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.controller.Controller;
import com.github.conagreen.controller.HelloController;
import com.github.conagreen.http.request.HttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 정의 컨트롤러 dispatcher resolver 테스트")
class UserDefinedRequestDispatchResolverTest {

    @DisplayName("해당 경로와 http method로 매핑된 컨트롤러가 존재하면 true")
    @Test
    void testSupport() {
        // given
        final RequestDispatchResolver sut = new UserDefinedRequestDispatchResolver();
        final String requestMessage = "GET /hello HTTP/1.1\r\n" +
                "header: value\r\n" +
                "\r\n";
        final InputStream in = new ByteArrayInputStream(requestMessage.getBytes());
        final HttpRequest request = new HttpRequest(in);

        // when
        final boolean maybeTrue = sut.supports(request);

        // then
        assertTrue(maybeTrue);
    }

    @DisplayName("해당 경로와 http method로 매핑된 컨트롤러 반환")
    @Test
    void testDispatch() {
        // given
        final UserDefinedRequestDispatchResolver sut = new UserDefinedRequestDispatchResolver();
        String requestMessage = "GET /hello HTTP/1.1\r\n" +
                "header: value\r\n" +
                "\r\n";
        final InputStream in = new ByteArrayInputStream(requestMessage.getBytes());
        final HttpRequest request = new HttpRequest(in);

        // when
        final Controller hello = sut.dispatch(request);

        // then
        assertTrue(hello instanceof HelloController);
    }

}