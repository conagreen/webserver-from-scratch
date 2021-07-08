package com.github.conagreen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QueryString {

    private final String rawQueryString;
    private final Map<String, String> parameterMap = new HashMap<>();

    public QueryString(String rawUri) {
        this.rawQueryString = parseQueryString(rawUri);
        if (this.rawQueryString != null) {
            Arrays.stream(this.rawQueryString.split("&"))
                    .forEach(kv -> {
                        final String[] keyAndValue = kv.split("=");
                        parameterMap.put(keyAndValue[0].trim(), keyAndValue[1].trim());
                    });
        }
    }

    private String parseQueryString(String rawUri) {
        return rawUri.contains("?") ? rawUri.split("\\?")[1] : null;
    }

    public String getRawQueryString() {
        return rawQueryString;
    }

    public String getParameter(String key ) {
        return parameterMap.get(key);
    }
}
