package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lufengxue.elevator.pojo.elevatorPO.Elevator;
import org.lufengxue.elevator.pojo.elevatorPO.Floor;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
@Mapper
public interface ElevatorMapper  {



    List<Floor> findFloor(String floorName);

    Floor queryFloor(Integer floorNumber,String floorName);

    Elevator queryElevator();

    List<Floor> callElevator(String floorName, Integer floorNumber, String buttons);

    void updateInFloor(Integer inFloor);



}
