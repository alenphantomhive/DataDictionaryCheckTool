package com.konka.data.exception;

/**
 * 业务消息
 *
 * @author framework-generator
 * @version 1.0.0
 * @date 2018-07-18
 */
public class BusinessException extends RuntimeException{

    private Integer code;

    private String msg;

    /**
     * 构造方法
     *
     * @param code
     * @param msg
     */
    public BusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
