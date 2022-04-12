package org.lufengxue.elevator.feign;

import com.github.pagehelper.PageInfo;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;
import org.lufengxue.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
@FeignClient(name="elevator")
@RequestMapping("/elevator")
public interface ElevatorFeign {

    /***
     * Elevator
     * @param elevator
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@RequestBody(required = false) ElevatorPo elevatorPo, @PathVariable(name="page")  int page, @PathVariable(name="size")  int size);

    /***
     * Elevator分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    Result<PageInfo> findPage(@PathVariable(name="page")  int page, @PathVariable(name="size")  int size);

    /***
     * 多条件搜索品牌数据
     * @param elevator
     * @return
     */
    @PostMapping(value = "/search" )
    Result<List<ElevatorPo>> findList(@RequestBody(required = false) ElevatorPo elevator);

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    Result delete(@PathVariable Integer id);

    /***
     * 修改Elevator数据
     * @param elevator
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    Result update(@RequestBody ElevatorPo elevator,@PathVariable(name="id") Integer id);

    /***
     * 新增Elevator数据
     * @param elevator
     * @return
     */
    @PostMapping
    Result add(@RequestBody ElevatorPo elevator);

    /***
     * 根据ID查询Elevator数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<ElevatorDto> findById(@PathVariable(name="id") Integer id);

    /***
     * 查询Elevator全部数据
     * @return
     */
    @GetMapping
    Result<List<ElevatorDto>> findAll();
}