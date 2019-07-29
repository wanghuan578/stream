package com.spirit.common.Exception;

import com.spirit.common.constant.ResultType;

public class MainStageException extends Exception {

    private ResultType type;

    public MainStageException(ResultType type) {
        this.type = type;
    }
    public ResultType getResultType() {
        return type;
    }
    public String getCode() {
        return type.code();
    }
    public String getText() {
        return type.text();
    }
}
