package com.shsxt.crm.model;

/**
 * Created by xlf on 2018/10/13.
 */
public class ResultInfo {

    private Integer code = 200;
    private String msg = "操作成功";

    public ResultInfo() {
    }

    public ResultInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(String msg) {
        this.msg = msg;
    }

    public ResultInfo(Integer code) {
        this.code = code;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
