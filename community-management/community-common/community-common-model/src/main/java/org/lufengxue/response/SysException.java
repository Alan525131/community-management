package org.lufengxue.response;


import org.lufengxue.enums.GeneralExcEnum;

/**
 * 错误来源于当前系统:
 * 往往是业务逻辑出错
 * 或程序健壮性差等问题
 */
public class SysException extends BaseException {

    public SysException(GeneralExcEnum excEnum) {
        super(excEnum);
    }

    public SysException(GeneralExcEnum excEnum, Throwable e) {
        super(excEnum, e);
    }

}
