package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lufengxue.elevator.pojo.elevatorPO.Elevator;
import org.lufengxue.elevator.pojo.elevatorPO.Floor;

import java.util.List;
import java.util.Set;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
@Mapper
public interface ElevatorMapper  {


    /**
     *  根据大楼名查询出对应的所有楼层
     * @param floorName
     * @return
     */
    List<Floor> findFloor(String floorName);

    /**
     * <!根据楼层号查询楼层状态与楼层高度-->
     * @param floorNumber
     * @param floorName
     * @return
     */
    Floor queryFloor(Integer floorNumber,String floorName);

    /**
     * 查询 电梯表中的电梯速度 与电梯所在楼层
     * @return
     */
    Elevator queryElevator();

    /**
     * 调用电梯来接用用户接口
     * @param floorName
     * @param floorNumber
     * @param buttons
     * @return
     */
    List<Floor> callElevator(String floorName, Integer floorNumber, String buttons);

    /**
     * 每次电梯停下之后,把电梯位置设置回电梯表中
     * @param inFloor
     */
    void updateInFloor(Integer inFloor);


    /**
     * 根据用户输入的目标楼层运行电梯接送用户到目的地
     * @param floorName
     * @param floorNumber
     * @param elevator
     * @return
     */
    List<Elevator> runElevator(String floorName, Integer floorNumber, Elevator elevator);
}
