package com.mhmmd.snappnews.utils;

public class Resource<T> {
    private Status status;
    private T data;
    private String msg;

    public Resource(Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
