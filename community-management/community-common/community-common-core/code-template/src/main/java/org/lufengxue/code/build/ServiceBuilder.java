package org.lufengxue.code.build;

import java.util.Map;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 */
public class ServiceBuilder {


    /***
     * 构建Service
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        //生成Service层文件
        BuilderFactory.builder(modelMap,
                "/template/service",
                "Service.java",
                TemplateBuilder.PACKAGE_SERVICE_INTERFACE,
                "Service.java");
    }

}
