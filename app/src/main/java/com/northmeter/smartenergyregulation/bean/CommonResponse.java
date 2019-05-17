package com.northmeter.smartenergyregulation.bean;

import java.io.Serializable;

/**
 * Created by dyd on 2018/8/31.
 */

public class CommonResponse implements Serializable {
    protected int code;
    protected String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
