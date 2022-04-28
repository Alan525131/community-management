package org.lufengxue.elevator.controller;

import io.swagger.annotations.ApiOperation;
import org.lufengxue.pojo.elevator.elevatorDto.Elevator;
import org.lufengxue.pojo.elevator.elevatorDto.Floor;
import org.lufengxue.elevator.service.ElevatorService;
import org.lufengxue.enums.StatusCode;
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
@RequestMapping("/elevator")
public class ElevatorController {

    @Autowired
    private ElevatorService elevatorService;


    @GetMapping("/findFloor")
    @ApiOperation("根据大楼名字查询所有楼层")
    public Result<List<Floor>> findFloor(@RequestParam(value = "floorNumber", required = true) String floorName) {
        List<Floor> floorList = elevatorService.findFloor(floorName);
//        return new Result<>(true, StatusCode.OK, "查询大楼数据成功", floorList);
        return new Result<>("DEFAULT_SUCCEED_CODE","DEFAULT_SUCCEED_MSG",floorList);
    }

    @GetMapping("/callElevator")
    @ApiOperation("根据当前大楼名字,与用户当前楼层号,电梯上下按钮来进行调度电梯接用户")
    public Result<Elevator> callElevator(@RequestParam(value = "floorName", required = true) String floorName,
                                            @RequestParam(value = "buttons", required = true) String buttons,
                                            @RequestParam(value = "floorNumber", required = true) Integer floorNumber) {
        Elevator elevator = elevatorService.callElevator(floorName, buttons, floorNumber);

        return new Result<>("DEFAULT_SUCCEED_CODE","DEFAULT_SUCCEED_MSG",elevator);
    }

    @PostMapping("/runElevator")
    @ApiOperation("根据用户输入的目标楼层集合运行电梯接送用户到目的地")
    public Result<List<Elevator>> runElevator(@RequestParam(value = "floorButtons", required = true) Set<Integer> floorButtons,
                                              @RequestParam(value = "id") Integer id) {
        List<Elevator> elevatorList = elevatorService.runElevator(floorButtons,id);
        return new Result<>("DEFAULT_SUCCEED_CODE","DEFAULT_SUCCEED_MSG", elevatorList);
    }
}
