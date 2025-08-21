package com.assignment.rateLimiter.dto;

public class RequestContext {

    private  final  String api;
    private  final String method;
    private  final  String userId;
    private  final  String clientId;
    private  final  String ip;


    public String getApi() {
        return api;
    }

    public String getMethod() {
        return method;
    }

    public String getUserId() {
        return userId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getIp() {
        return ip;
    }

    public RequestContext(String api, String method, String userId, String clientId, String ip) {
        this.api = api;
        this.method = method;
        this.userId = userId;
        this.clientId = clientId;
        this.ip = ip;
    }

    public String api() { return api; }
    public String method() { return method; }
    public String userId() { return userId; }
    public String clientId() { return clientId; }
    public String ip() { return ip; }

}
