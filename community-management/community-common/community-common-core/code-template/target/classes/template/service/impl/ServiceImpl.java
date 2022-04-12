package $ import org.springframework.stereotype.Service;

{package_service_impl};
        {package_mapper}.${Table}Mapper;
        {package_pojo}.${Table};
        {package_service}.${Table}Service;
/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ${Table}业务接口实现类
 */
@Service
public class ${Table}ServiceImpl extends CoreServiceImpl<${Table}> implements ${Table}Service {

    private ${Table}Mapper ${table}Mapper;

    @Autowired
    public ${Table}ServiceImpl(${Table}Mapper ${table}Mapper) {
        super(${table}Mapper, ${Table}.class);
        this.${table}Mapper = ${table}Mapper;
    }
}
