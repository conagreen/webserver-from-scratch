package com.github.conagreen.http.session;

import com.github.conagreen.http.Cookie;
import com.github.conagreen.http.request.HttpRequest;
import com.github.conagreen.http.response.HttpResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

// 세션 관리
// 싱글톤
public class SessionManager {

    private static final String SESSION_NAME = "SESSIONID";
    private final Map<String, HttpSession> sessionMap = new HashMap<>();
    private static final SessionManager INSTANCE = new SessionManager();

    // prevent to instantiate
    private SessionManager() {
    }

    public static HttpSession getHttpSession(HttpRequest request, HttpResponse response) {
        return INSTANCE.getSession(request, response);
    }

    private HttpSession getSession(HttpRequest request, HttpResponse response) {
        final Cookie cookie = request.getCookie(SESSION_NAME);
        if (cookie != null) return Optional
                .ofNullable(sessionMap.get(cookie.getValue()))
                .orElseGet(() -> createSession(response));
        else return createSession(response);
    }

    private HttpSession createSession(HttpResponse response) {
        final HttpSession httpSession = new HttpSession(UUID.randomUUID().toString());
        sessionMap.put(httpSession.getId(), httpSession);
        final Cookie cookie = new Cookie(SESSION_NAME, httpSession.getId());
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return httpSession;
    }

    public static void remove(String id) {
        INSTANCE.removeSession(id);
    }

    private void removeSession(String id) {
        sessionMap.remove(id);
    }
}
