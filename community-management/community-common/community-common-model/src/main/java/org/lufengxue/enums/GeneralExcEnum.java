package org.lufengxue.enums;


import org.lufengxue.constant.HttpStatus;

/**
 * 通用异常枚举
 * <br/>
 * 可用于可抛异常
 * {@link BaseException#BaseException(GeneralExcEnum)}
 * {@link BaseException#BaseException(GeneralExcEnum, Throwable)}
 * <br/>
 * 也可用于请求失败返回 {@link RestVO#fail(BaseException)}
 */
public enum GeneralExcEnum {

    /* A 类错误, 错误来源于用户 */
    A_BAD_REQUEST       ("A", "A4000", HttpStatus.BAD_REQUEST,  true, "错误的请求"),
    A_ILLEGAL_ARG_ERR   ("A", "A4001", HttpStatus.BAD_REQUEST,  true, "非法参数"),
    A_NAME_PWD_ERR      ("A", "A4010", HttpStatus.UNAUTHORIZED, true, "用户名或密码错误"),
    A_USERNAME_ERR      ("A", "A4011", HttpStatus.UNAUTHORIZED, true, "用户名错误"),
    A_PASSWORD_ERR      ("A", "A4012", HttpStatus.UNAUTHORIZED, true, "用户密码错误"),
    A_TOKEN_ERR         ("A", "A4013", HttpStatus.UNAUTHORIZED, true, "令牌无效或过期"),
    A_NOT_LOGIN         ("A", "A4014", HttpStatus.UNAUTHORIZED, true, "未登录"),
    A_ACCESS_DENIED     ("A", "A4030", HttpStatus.FORBIDDEN,    true, "拒绝访问"),
    A_NOT_FOUND         ("A", "A4040", HttpStatus.NOT_FOUND,    true, "资源未找到"),



    /* B 类错误, 错误来源于当前系统 */
    B_SYSTEM_ERR        ("B", "B0001", HttpStatus.INTERNAL_ERROR, true, "系统内部错误"),



    /* C 类错误, 错误来源于第三方服务 */
    C_THIRD_TIME_OUT    ( "C", "C0001", HttpStatus.UNAVAILABLE, true,"第三方响应超时"),


    ;


    /**
     * 错误类型
     * A 类错误, 错误来源于用户
     * B 类错误, 错误来源于当前系统
     * C 类错误, 错误来源于第三方服务
     */
    public final String type;


    /**
     * 错误码, 错误的唯一标识
     */
    public final String code;


    /**
     * 请求响应状态码
     * {@link HttpStatus}
     */
    public final int status;


    /**
     * 最终前端用户是否可见错误消息
     * true: 前端用户可见
     * false: 仅开发可见
     */
    public final boolean visible;


    /**
     * 错误消息, 对与错误的描述
     */
    public final String msg;


    GeneralExcEnum(String type, String code, int status, boolean visible, String msg) {
        this.type = type;
        this.code = code;
        this.status = status;
        this.visible = visible;
        this.msg = msg;
    }

}
