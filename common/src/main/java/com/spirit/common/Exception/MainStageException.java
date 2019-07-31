package com.spirit.common.Exception;

import com.spirit.common.constant.ResultType;

public class MainStageException extends Exception {

    private ResultType type;
    private String code;
    private String text;

    public MainStageException(String code, String text) {
        this.code = code;
        this.text = text;
    }
    public MainStageException(ResultType type) {
        this.type = type;
    }
    public ResultType getResultType() {
        return type;
    }
    public String getCode() {
        return type != null ? type.code() : code;
    }
    public String getText() {
        return type != null ? type.text() : text;
    }
}
