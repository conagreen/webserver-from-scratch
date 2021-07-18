package com.github.conagreen.webserver.dispatcher;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사용자 정의 컨트롤러 dispatcher resolver 테스트")
class UserDefindRequestDispatchResolverTest {

    @DisplayName("해당 경로와 http method로 매핑된 컨트롤러가 존재하면 true")
    @Test
    void testSupport() {
        // system under test
        final RequestDispatchResolver sut = new UserDefindRequestDispatchResolver();
    }

    @DisplayName("디스패치 테스트")
    @Test
    void testDispatch() {

    }

}