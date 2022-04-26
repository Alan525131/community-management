package org.lufengxue.response;

public enum ResponseEnum {

    MISSION_OK("2000", "任务成功"),
    MISSION_ERROR("2001","任务失败"),
    LOGINERROR("2002","用户名或密码错误"),
    ACCESSERROR("20003","权限不足"),
    REMOTEERROR("20004","远程调用失败"),
    REPERROR("20005","重复操作"),
    NOTFOUNDERROR("20006","没有对应的数据");

    private String code;
    private String message;

    private ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String ResponseEnum(String code) {
       ResponseEnum[] var1 = (ResponseEnum[]) ResponseEnum.class.getEnumConstants();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ResponseEnum item = var1[var3];
            if (item.getCode().equals(code)) {
                return item.getMessage();
            }
        }

        return "";
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
