package com.github.conagreen.webserver.dispatcher;

import com.github.conagreen.http.request.HttpMethod;

import java.util.Objects;

class RequestKey {
    private final String uri;
    private final HttpMethod method;

    public RequestKey(String uri, HttpMethod method) {
        this.uri = uri;
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestKey that = (RequestKey) o;
        return Objects.equals(uri, that.uri) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, method);
    }
}