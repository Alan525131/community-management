package org.lufengxue.elevator.service;

import org.lufengxue.elevator.pojo.elevatorPO.Elevator;
import org.lufengxue.elevator.pojo.elevatorPO.Floor;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口
 */
public interface ElevatorService  {

    List<Floor> findFloor(String floorName);

    List<Floor> callElevator(String floorName, String buttons, Integer floorNumber);

    List<Elevator> runElevator(Integer status, Set<Integer> floorButtons, Integer sports);
//List<Elevator> runElevator(Elevator elevator);

}
