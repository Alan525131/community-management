package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lufengxue.pojo.elevator.elevatorPO.Elevator;
import org.lufengxue.pojo.elevator.elevatorPO.Floor;

import java.util.List;
import java.util.Set;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
@Mapper
public interface ElevatorMapper {


    /**
     * 根据大楼名查询出对应的所有楼层
     *
     * @param floorName
     * @return
     */
    List<Floor> findFloor(String floorName);

    /**
     * 根据id 查询对应电梯列表数据
     *  @param id 电梯对象 id
     * @return 返回一个电梯对象
     */
    Elevator queryElevator(Integer id);

    /**
     *  调用电梯来接用用户接口
     * @param inFloor 电梯所在楼层
     * @return 返回 一个用户乘坐的电梯对象id
     */
    Floor callElevator(Integer inFloor);

    /**
     * 每次电梯停下之后,把电梯位置设置回电梯表中
     * @param inFloor 电梯楼层
     * @param sports  电梯运行状态
     * @param status   电梯是否可用
     * @param buildingId   电梯对应的大楼id
     */
    void updateInFloor(Integer inFloor, Integer sports, Integer status, Integer buildingId);


    /**
     *  根据用户输入的目标楼层运行电梯接送用户到目的地
     * @param floorButtons 目标楼层集合
     * @return  返回一个用户运行的电梯对象详情
     */
    List<Elevator> runElevator(Set<Integer> floorButtons);

    /**
     *  查询所有的电梯
     * @param floorName 大楼名称
     * @return 返回对应大楼的所有电梯对象
     */
    List<Elevator> findElevator(String floorName);

    /**
     *  根据电梯id查询对应大楼的所有楼层号
     * @param id 电梯id
     * @return
     */
    List<Floor> getFloorNumber(Integer id);

}
