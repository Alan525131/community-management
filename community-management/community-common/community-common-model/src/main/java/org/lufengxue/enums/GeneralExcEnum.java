package org.lufengxue.enums;


/**
 * 通用异常
 * <br/>
 * 可用于可抛异常
 * {@link BaseException#BaseException(org.alvin.deal.common.model.enums.GeneralExcEnum)}
 * {@link BaseException#BaseException(org.alvin.deal.common.model.enums.GeneralExcEnum, Exception)}
 * <br/>
 * 可用于请求失败返回 {@link RestVO#fail(BaseException)}
 */
public enum GeneralExcEnum {

    /* A 类错误, 错误来源于用户 */
    A_USERNAME_ERR(true, "A", "A0001", "用户名错误"),
    A_PASSWORD_ERR(true, "A", "A0002", "用户密码错误"),
    A_USERNAME_PASSWORD_ERR(true, "A", "A0003", "用户名或密码错误"),
    A_ILLEGAL_ARG_ERR(false, "A", "A0004", "非法参数"),





    /* B 类错误, 错误来源于当前系统 */
    B_SYSTEM_ERR(true, "B", "B0001", "系统内部错误"),
    B_ACCESS_DENIED(true, "B", "B0002", "拒绝访问"),
    B_ACCESS_UNAUTHORIZED(true, "B", "B0003", "无权访问"),



    /* C 类错误, 错误来源于第三方服务 */
    C_THIRD_TIME_OUT(true, "C", "C0001", "第三方响应超时"),


    ;



    /**
     * 最终用户是否可见错误消息
     * true: 用户可见
     * false: 仅开发可见
     */
    public boolean visible;

    /**
     * 错误类型
     * A 类错误, 错误来源于用户
     * B 类错误, 错误来源于当前系统
     * C 类错误, 错误来源于第三方服务
     */
    public String type;

    /**
     * 错误码
     */
    public String code;

    /**
     * 错误消息
     */
    public String msg;


    GeneralExcEnum(boolean visible, String type, String code, String msg) {
        this.visible = visible;
        this.type = type;
        this.code = code;
        this.msg = msg;
    }

}
