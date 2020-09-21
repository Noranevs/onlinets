package com.onlinets.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiao on 2018/10/27.
 * 客户端状态码响应类
 */
public class LayDataMessage {
    private int code;//状态码
    private String msg;//响应信息
    private Map<String, Object> data = new HashMap();//返回内容

    public static LayDataMessage success() {
        return new LayDataMessage(0, "处理成功！");
    }

    public static LayDataMessage fail() {
        return new LayDataMessage(1, "处理失败！");
    }

    public static LayDataMessage error(String msg) {
        return new LayDataMessage(1, msg);
    }

    public static LayDataMessage exception() {
        return new LayDataMessage(2, "处理异常！");
    }

    public LayDataMessage add(String key, Object o) {
        this.data.put(key, o);
        return this;
    }

    public LayDataMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayDataMessage() {
    }

    public LayDataMessage(int code, String msg, Map<String, Object> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public LayDataMessage(int code) {
        this.code = code;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
