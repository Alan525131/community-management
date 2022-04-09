package org.lufengxue.code.build;

import java.util.Map;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 */
public class FeignBuilder {


    /***
     * 构建Feign
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        //生成Dao层文件
        BuilderFactory.builder(modelMap,
                "/template/feign",
                "Feign.java",
                TemplateBuilder.PACKAGE_FEIGN,
                "Feign.java");
    }

}
