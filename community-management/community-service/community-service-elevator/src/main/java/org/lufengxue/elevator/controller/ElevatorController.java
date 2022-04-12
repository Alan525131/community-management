package org.lufengxue.elevator.controller;

import org.lufengxue.core.AbstractCoreController;
import org.lufengxue.elevator.pojo.elevatorDto.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;
import org.lufengxue.elevator.service.ElevatorService;
import org.lufengxue.enums.StatusCode;
import org.lufengxue.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： controller
 */


@RestController
@RequestMapping("/elevator")
public class ElevatorController extends AbstractCoreController<ElevatorPo> {

    private ElevatorService  elevatorService;

    @Autowired
    public ElevatorController(ElevatorService  elevatorService) {
        super(elevatorService, ElevatorPo.class);
        this.elevatorService = elevatorService;
    }

    @PostMapping("/floor")
    public Result buttonElevator(@RequestBody ElevatorPo elevatorPo){
        ElevatorDto elevatorDto = elevatorService.buttonElevator(elevatorPo);
        return new Result(true, StatusCode.OK,"电梯运行成功",elevatorDto);
    }
    @PostMapping("/button")
    public Result clickButton(@RequestBody ElevatorPo elevatorPo){
        ElevatorDto elevatorDto = elevatorService.clickButton(elevatorPo);
        return new Result(true, StatusCode.OK,"电梯按钮正常到达成功",elevatorDto);


    }
    @RequestMapping("/test")
    public String test(String name){
        return name ;
    }
}
