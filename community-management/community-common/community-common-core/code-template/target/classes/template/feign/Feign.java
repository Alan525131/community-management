package com.changgou.content.feign;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ${Table}Dao
 */
@FeignClient(name="${serviceName}")
@RequestMapping("/${table}")
public interface ${Table}Feign {

    /***
     * ${Table}
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) ${Table} ${table}, @PathVariable(name="page")  int page, @PathVariable(name="size")  int size);

    /***
     * ${Table}分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name="page")  int page, @PathVariable(name="size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param ${table}
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<${Table}>> findList(@RequestBody(required = false) ${Table} ${table});

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable ${keyType} id);

    /***
     * 修改${Table}数据
     * @param ${table}
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ${Table} ${table},@PathVariable(name="id") ${keyType} id);

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    @PostMapping
    Result add(@RequestBody ${Table} ${table});

    /***
     * 根据ID查询${Table}数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<${Table}> findById(@PathVariable(name="id") ${keyType} id);

    /***
     * 查询${Table}全部数据
     * @return
     */
    @GetMapping
    Result<List<${Table}>> findAll();
}