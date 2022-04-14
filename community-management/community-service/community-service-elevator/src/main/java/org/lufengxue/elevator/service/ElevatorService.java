package org.lufengxue.elevator.service;

import org.lufengxue.elevator.pojo.elevatorDto.CallElevaterDto;
import org.lufengxue.elevator.pojo.elevatorDto.GoTargetDto;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口
 */
public interface ElevatorService  {




    GoTargetDto targetElevator(List<Integer> targetLevels);

    CallElevaterDto callElevator(Integer meLevel,Boolean isDown);

    String findState();
}
