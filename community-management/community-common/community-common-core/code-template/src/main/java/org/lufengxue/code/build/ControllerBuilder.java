package org.lufengxue.code.build;

import java.util.Map;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 */
public class ControllerBuilder {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        //生成Controller层文件
        BuilderFactory.builder(modelMap,
                "/template/controller",
                "Controller.java",
                TemplateBuilder.PACKAGE_CONTROLLER,
                "Controller.java");
    }

}
