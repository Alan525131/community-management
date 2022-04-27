package org.lufengxue.response;


import org.lufengxue.enums.GeneralExcEnum;

/**
 * 错误来源于第三方服务:
 * 比如第三方支付,
 * 第三方翻译等
 * 消息投递超时等问题
 */
public class ThirdException extends BaseException {


    public ThirdException(GeneralExcEnum excEnum) {
        super(excEnum);
    }

    public ThirdException(GeneralExcEnum excEnum, Throwable e) {
        super(excEnum, e);
    }
}
