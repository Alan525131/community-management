package org.lufengxue.exception.global;

/**
 * 自定义异常
 */
public class CustomException extends RuntimeException {

    private Integer code;

    public CustomException() {
    }

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
