package org.lufengxue.exception.global;

/**
 *
 * 业务异常
 */
public class BusinessException extends RuntimeException{
    private Integer code;
    public BusinessException() {
    }

    public BusinessException(String msg,Integer code) {
        super(msg);
        this.code = code;

    }

    public BusinessException(String msg) {
    }

    public Integer getCode() {
        return code;
    }
}
