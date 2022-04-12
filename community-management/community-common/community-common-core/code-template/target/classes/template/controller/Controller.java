package ${package_controller};
        {package_pojo}.${Table};
        {package_service}.${Table}Service;
<#if swagger==true>
</#if>

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
