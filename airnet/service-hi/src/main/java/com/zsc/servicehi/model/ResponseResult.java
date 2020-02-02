package com.zsc.servicehi.model;

import java.io.Serializable;

public class ResponseResult implements Serializable {

    private Boolean msg;

    private Object data;

    public Boolean getMsg() {
        return msg;
    }

    public void setMsg(Boolean msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
