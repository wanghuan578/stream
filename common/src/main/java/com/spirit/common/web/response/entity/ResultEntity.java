package com.spirit.common.web.response.entity;

import com.spirit.common.web.response.constant.ResultType;
import lombok.Data;
import java.io.Serializable;

@Data
public class ResultEntity<T> implements Serializable {

    private static final long serialVersionUID = -3581261863966039090L;

    private String code;
    private String text;
    private T content;

    public ResultEntity(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public ResultEntity(String code, String text, T obj) {
        this.code = code;
        this.text = text;
        this.content = obj;
    }

    public ResultEntity() {
//        this.errorCode = ResultType.SUCCESS.code();
//        this.errorText = ResultType.SUCCESS.text();
    }

    public ResultEntity succeed() {
        return new ResultEntity(ResultType.SUCCESS.code(), ResultType.SUCCESS.text());
    }

    public ResultEntity<T> succeed(T t) {
        return new ResultEntity<T>(ResultType.SUCCESS.code(), ResultType.SUCCESS.text(), t);
    }

    public ResultEntity succeed(String msg, Object obj) {
        return new ResultEntity<Object>(ResultType.SUCCESS.code(), msg, obj);
    }

    public static ResultEntity failed(ResultType type) {
        return new ResultEntity<Object>(type.code(), type.text(), null);
    }

    public static ResultEntity failed(ResultType type, Object obj) {
        return new ResultEntity<Object>(type.code(), type.text(), obj);
    }
}
