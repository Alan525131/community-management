package org.lufengxue.response;


import org.lufengxue.enums.GeneralExcEnum;

/**
 * 错误来源于用户:
 * 比如参数错误，
 * 用户安装版本过低，
 * 用户操作超时等问题；
 */
public class UserException extends BaseException {


    public UserException(GeneralExcEnum excEnum) {
        super(excEnum);
    }

    public UserException(GeneralExcEnum excEnum, Throwable e) {
        super(excEnum, e);
    }
}
