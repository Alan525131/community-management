package ${package_controller};
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import com.github.pagehelper.PageInfo;
import response.Result;
import enums.StatusCode;
<#if swagger==true>import io.swagger.annotations.*;</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.changgou.core.AbstractCoreController;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： controller
 */


@RestController
@RequestMapping("/${table}")
@CrossOrigin
public class ${Table}Controller extends AbstractCoreController<${Table}>{

    private ${Table}Service  ${table}Service;

    @Autowired
    public ${Table}Controller(${Table}Service  ${table}Service) {
        super(${table}Service, ${Table}.class);
        this.${table}Service = ${table}Service;
    }
}
