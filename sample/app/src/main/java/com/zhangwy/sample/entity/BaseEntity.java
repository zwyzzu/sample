package com.zhangwy.sample.entity;

import java.io.Serializable;

/**
 * Created by 张维亚(zhangwy) on 2016/12/20 下午4:46.
 * Updated by zhangwy on 2016/12/20 下午4:46.
 * Description base entity
 */
public class BaseEntity implements Serializable {
    private int code;
    private String msg;

    public BaseEntity() {
        super();
    }

    public BaseEntity(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

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
