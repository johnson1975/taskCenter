package com.chaojidaogou.taskcenter.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/1/20.
 */
public class Response {
    private Integer result;
    private static final Integer SUCCESS = 1;
    private static final Integer FAIL = 0;
    private Map<String, String> headers = new HashMap<>();
    private Integer status;
    private Object body;

    public Response(Object body) {
        this(body, SUCCESS);
    }

    public Response(Object body, Integer status) {
        this.body = body;
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void removeHeader(String key) {
        headers.remove(key);
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static Response successResponse(Object body) {
        return new Response(body);
    }

    public static Response failResponse(Object body) {
        return new Response(body, 0);
    }
}
