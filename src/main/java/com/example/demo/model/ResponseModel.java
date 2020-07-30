package com.example.demo.model;

public class ResponseModel {
    private String msg;
    private int status;
    private Object data;

    public ResponseModel(String msg, int status, Object data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
