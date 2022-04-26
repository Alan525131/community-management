package org.lufengxue.exception.global;

import lombok.Data;

/**
 *  业务代码异常
 */
@Data
public class BusinessCodeException extends BusinessException {

    private Integer code;

    public BusinessCodeException( String msg,Integer code) {
        super(msg);
        this.code = code;
    }
}
