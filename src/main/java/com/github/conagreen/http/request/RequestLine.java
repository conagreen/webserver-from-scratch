package com.github.conagreen.http.request;

import com.github.conagreen.http.request.HttpMethod;
import com.github.conagreen.http.request.QueryString;

public class RequestLine {

    private static final int TOKEN_METHOD_PART = 0;
    private static final int TOKEN_URI_PART = 1;

    private final HttpMethod method;
    private final String uri;
    private final QueryString queryString;

    // GET / HTTP/1.1
    public RequestLine(String rawRequestLine) {
        final String[] tokens = rawRequestLine.split(" ");
        this.method = parseMethod(tokens[TOKEN_METHOD_PART]);
        this.uri =  parseUri(tokens[TOKEN_URI_PART]);
        this.queryString = new QueryString(tokens[TOKEN_URI_PART]);
    }

    private HttpMethod parseMethod(String token) {
        return HttpMethod.valueOf(token);
    }

    private String parseUri(String rowUri) {
        return rowUri.contains("?") ? rowUri.split("\\?")[0] : rowUri;
    }

    public HttpMethod httpMethod() {
        return method;
    }

    public String requestUri() {
        return uri;
    }

    public String getQueryString() {
        return queryString.getRawQueryString();
    }

    public String getParameter(String key) {
        return queryString.getParameter(key);
    }

}
