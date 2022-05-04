package org.lufengxue.elevator.controller;

import io.swagger.annotations.*;
import org.lufengxue.pojo.elevator.elevatorDto.Elevator;
import org.lufengxue.pojo.elevator.elevatorDto.Floor;
import org.lufengxue.elevator.service.ElevatorService;
import org.lufengxue.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： controller
 */


@RestController
@Api(tags = "电梯管理类")
@RequestMapping("/elevator")
public class ElevatorController {

    @Autowired
    private ElevatorService elevatorService;


    @GetMapping("/findFloor")
    @ApiOperation("根据大楼名字查询所有楼层")
    @ApiImplicitParam(name = "buildingName", value = "大楼名称", dataType = "String", paramType = "query", required = true)
    public Result<List<Floor>> findFloor(String buildingName) {
        List<Floor> floorList = elevatorService.findFloor(buildingName);
        return new Result<>("DEFAULT_SUCCEED_CODE", "DEFAULT_SUCCEED_MSG", floorList);
    }

    @GetMapping("/callElevator")
    @ApiOperation("根据当前大楼名字,与用户当前楼层号,电梯上下按钮来进行调度电梯接用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "buildingName", value = "大楼名字", dataType = "string", required = true),
            @ApiImplicitParam(name = "buttons", value = "电梯按钮", dataType = "string", required = true),
            @ApiImplicitParam(name = "floorNumber", value = "大楼楼层号", dataType = "string", required = true),
    })
    public Result<Elevator> callElevator(@RequestParam(value = "buildingName") String buildingName,
                                         @RequestParam(value = "buttons") String buttons,
                                         @RequestParam(value = "floorNumber") Integer floorNumber) {
        Elevator elevator = elevatorService.callElevator(buildingName, buttons, floorNumber);

        return new Result<>("DEFAULT_SUCCEED_CODE", "DEFAULT_SUCCEED_MSG", elevator);
    }

    @PostMapping("/runElevator")
    @ApiOperation("根据用户输入的目标楼层集合运行电梯接送用户到目的地")
    public Result<List<Elevator>> runElevator( @RequestParam(value = "floorButtons") @ApiParam(value = "目标楼层列表",required = true) Set<Integer> floorButtons,
                                               @RequestParam(value = "id") @ApiParam(value = "运行的电梯id",required = true) Integer id)  {
        List<Elevator> elevatorList = elevatorService.runElevator(floorButtons, id);
        return new Result<>("DEFAULT_SUCCEED_CODE", "DEFAULT_SUCCEED_MSG", elevatorList);
    }
}
