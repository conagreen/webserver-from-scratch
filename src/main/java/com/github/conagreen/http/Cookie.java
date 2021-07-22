package com.github.conagreen.http;


public class Cookie {
    private final String key;
    private final String value;

    private Boolean secure = false;
    private Boolean httpOnly = false;
    private Integer maxAge = null;
    private String path = "/";
    private String domain = "localhost";

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Boolean getSecure() {
        return secure;
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    public Boolean getHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(Boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public String serialize() {
        final StringBuilder sb = new StringBuilder();
        sb.append(key).append("=").append(value).append("; ");

        if (maxAge != null)  {
            sb.append("Max-Age=").append(maxAge).append("; ");
        } else {
            sb.append("Max-Age=").append(0).append("; ");
        }

        sb.append("Domain=").append(domain).append("; ");
        sb.append("Path=").append(path).append("; ");

        if (secure) {
            sb.append("Secure; ");
        }

        if (httpOnly) {
            sb.append("httpOnly");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", secure=" + secure +
                ", httpOnly=" + httpOnly +
                ", maxAge=" + maxAge +
                '}';
    }
}
