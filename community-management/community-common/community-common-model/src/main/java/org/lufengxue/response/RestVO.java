package org.lufengxue.response;

import lombok.Getter;
import org.lufengxue.enums.GeneralExcEnum;

/**
 * 返回前端的数据模型.
 */
@Getter
public class RestVO<T> {
    /**
     * 默认成功编码: 00000, 成功消息: SUCCEED, 成功http状态: 200
     */
    protected static final String DEFAULT_SUCCEED_CODE = "00000";
    protected static final String DEFAULT_SUCCEED_MSG = "SUCCEED";

    /**
     * 返回编码
     */
    protected String code;

    /**
     * 返回信息
     */
    protected String msg;

    /**
     * 返回数据
     */
    protected T data;


    /**
     * 返回成功
     */
    public static <T> RestVO<T> success() {
        return new RestVO<>(DEFAULT_SUCCEED_CODE, DEFAULT_SUCCEED_MSG, null);
    }

    public static <T> RestVO<T> success(T data) {
        return new RestVO<>(DEFAULT_SUCCEED_CODE, DEFAULT_SUCCEED_MSG, data);
    }


    /**
     * 返回失败
     */
    public static <T> RestVO<T> fail(GeneralExcEnum excEnum) {
        setHttpStatus(excEnum.status);
        return new RestVO<T>(excEnum.code, excEnum.msg, null);
    }

    public static <T> RestVO<T> fail(BaseException e) {
        setHttpStatus(e.getStatus());
        return new RestVO<T>(e.getCode(), e.getMsg(), null);
    }


    /**
     * 全参构造
     */
    protected RestVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 设置响应状态码
     */
    protected static void setHttpStatus(int status) {
        /*HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
        if (response != null) {
            response.setStatus(status);
        }*/
    }

}

