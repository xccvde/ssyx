package com.ssyx.common.exception;

import com.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class SsyxException extends RuntimeException {

    private Integer code;

    public SsyxException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
