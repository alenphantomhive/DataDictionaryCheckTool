package com.konka.datademo.common;

/**
 * 统一返回消息体
 *
 * @author framework-generator
 * @version 1.0.0
 * @date 2018-07-18
 */
public class Response<T> {

    /**
     * 请求成功，返回消息
     */
    private final static String SUCCESS_MESSAGE = "success";

    /**
     * 请求成功，返回码
     */
    private final static Integer SUCCESS_CODE = 0;

    private Integer code;

    private String msg;

    private T data;

    public Response() {}

    public Response(T data) {

        this.code = SUCCESS_CODE;

        this.msg = SUCCESS_MESSAGE;

        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
