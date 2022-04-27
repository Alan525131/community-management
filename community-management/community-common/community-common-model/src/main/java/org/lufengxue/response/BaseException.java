package org.lufengxue.response;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.lufengxue.enums.GeneralExcEnum;

/**
 * 基础异常
 */
@Slf4j
@Getter
public abstract class BaseException extends RuntimeException {

    /**
     * 错误编码
     */
    protected String code;


    /**
     * 错误消息
     */
    protected String msg;


    /**
     * http 响应状态
     */
    protected int status;


    public BaseException(GeneralExcEnum excEnum) {
//        super(excEnum.msg);
        this.code = excEnum.code;
        this.msg = excEnum.msg;
        this.status = excEnum.status;
    }


    public BaseException(GeneralExcEnum excEnum, Throwable e) {
        super(String.format("[%s]%s: %s", e.getClass().getSimpleName(), e.getMessage(), excEnum.msg), e);
        this.code = excEnum.code;
        this.msg = excEnum.msg;
        this.status = excEnum.status;
    }

}