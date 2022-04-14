package org.lufengxue.elevator.controller;

import io.swagger.annotations.ApiOperation;
import org.lufengxue.elevator.pojo.elevatorDto.CallElevaterDto;
import org.lufengxue.elevator.pojo.elevatorDto.GoTargetDto;
import org.lufengxue.elevator.service.ElevatorService;
import org.lufengxue.enums.StatusCode;
import org.lufengxue.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： controller
 */


@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    @Autowired
    private ElevatorService  elevatorService;


    @PostMapping("/targetFloor")
    @ApiOperation(value = "根据客户点击的目标楼层，运行电梯送往目的地",notes = "电梯运送客户功能")
    public Result targetElevator(@RequestParam(name = "targetLevels") List<Integer> targetLevels ){
        GoTargetDto goTargetDto = elevatorService.targetElevator(targetLevels);
        return new Result(true, StatusCode.OK,"电梯运行成功",goTargetDto);
    }
    @PostMapping("/button")
    @ApiOperation(value = "根据用户 按键的楼层，和按键的上下 ，电梯来接用户",notes = "电梯接用户功能")
    public Result callElevator(@RequestParam(name = "meLevel") Integer meLevel,@RequestParam(name = "isDown") Boolean isDown ){
        CallElevaterDto callElevaterDto = elevatorService.callElevator(meLevel,isDown);
        return new Result(true, StatusCode.OK,"电梯按钮正常到达成功",callElevaterDto);


    }
    @GetMapping("/ttt")
    public String findString(@RequestParam(name = "name") String name){
        return "wangwu";
    }


    @GetMapping("/State")
    @ApiOperation(value = "查询电梯状态",notes = "查询数据库电梯状态")
    public String findState() {
        return elevatorService.findState();
    }
}
