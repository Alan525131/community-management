package org.lufengxue.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.lufengxue.enums.StatusCode;

import java.io.Serializable;


/**
 * 接收消息封装对象
 */
@ApiModel
public class Result<T> implements Serializable {


    @ApiModelProperty(required = true, value = "成功与否的标识 true 为成功 false 为失败")
    private boolean flag;
    @ApiModelProperty(required = true, value = "返回状态码 20000 为成功 40001 为失败")
    private Integer code;
    @ApiModelProperty(required = true, value = "返回逻辑提示信息")
    private String message;
    @ApiModelProperty(required = false, value = "返回逻辑数据")
    private T data;

    public static <T> Result ok(T data) {
        return new Result(true, StatusCode.OK, "操作成功", data);
    }

    public static <T> Result ok() {
        return new Result(true, StatusCode.OK, "操作成功");
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
