package org.lufengxue.code.swagger;


/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ：org.lufengxue.code.util
 * 响应数据配置
 */
public class SwaggerResponse {
    //响应状态码  200,404.。。
    private int code;

    //描述
    private String description;

    //响应引用
    private String schema;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}
