package org.lufengxue.elevator.service;

import org.lufengxue.core.service.CoreService;
import org.lufengxue.elevator.pojo.elevatorDto.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口
 */
public interface ElevatorService extends CoreService<ElevatorPo> {

    ElevatorDto buttonElevator(ElevatorPo elevatorPo);
}
