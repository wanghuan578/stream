package com.spirit.common.constant;

import com.spirit.common.web.response.utils.ErrorTuple;




public enum ResultType implements ErrorTuple {

    SUCCESS("0000", "OK"),
    PARAMS_DISMATCH("0001", "参数错误"),
    ERROR_FFMPEG("0002", "转码失败"),
    UPLOAD_THREAD_POOL_FULL("0003", "上传线程池已满"),
    ;

    private String code;
    private String text;

    ResultType(String code, String name) {
        this.code = code;
        this.text = name;
    }

    public String code() {
        return code;
    }

    public String text() {
        return text;
    }
}
