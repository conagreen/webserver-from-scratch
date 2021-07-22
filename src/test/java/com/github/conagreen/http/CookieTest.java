package com.github.conagreen.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("쿠키 테스트")
class CookieTest {

    @DisplayName("쿠키 만들기")
    @Test
    void test() {
        final Cookie cookie = new Cookie("yummy_cookie", "choco");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(86400);
        assertEquals("yummy_cookie=choco; Max-Age=86400; Domain=localhost; Path=/; Secure; httpOnly", cookie.serialize());
    }

}