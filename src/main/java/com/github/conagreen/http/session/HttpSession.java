package com.github.conagreen.http.session;

public class HttpSession {

    // JSESSIONID=KS2N3D222DD3K234L32JL3S34K2F4LDS
    public String getId() {
        return "";
    }

    public Object getAttribute(String key) {
        return null;
    }

    public void setAttribute(String key, Object value) {

    }

    /**
     * 1. 서버의 세션 저장소에서 세션 무효화
     * 2. 클라의 세션 값 삭제 (max-age=0)
     */
    public void invalidate() {

    }
}
