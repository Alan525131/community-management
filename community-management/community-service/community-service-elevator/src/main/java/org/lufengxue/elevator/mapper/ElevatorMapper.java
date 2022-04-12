package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.lufengxue.elevator.pojo.elevatorDto.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
public interface ElevatorMapper extends Mapper<ElevatorPo> {
    @Select("SELECT floor_name, current_teme, end_teme,target_floor,floor_number,elevator_height,speed  FROM `ele_elevator` ")
    ElevatorDto clickButton(ElevatorPo elevatorPo);

    @Update("update ele_elevator set\n" +
            "            floor_name =#{floorName},elevator_state =#{elevatorState},button_up =#{upButton},button_next =#{nextButton}，floor_level =#{floorLevel},button =#{button},\n" +
            "\t\t\televator_height =#{elevatorHeight}, current_teme =#{currentTeme},speed =#{speed},teme =#{teme},current_teme =#{currentTeme}, end_teme =#{endTeme},\n" +
            "\t\t\tfloor_top =#{floorTop},floor_bottom =#{floorBottom},target_floor =#{targetFloor},floor_number =#{floorNumber},floor_open =#{floorOpen},floor_off =#{floorOff} where id =#{id}")
    ElevatorDto buttonElevator(ElevatorPo elevatorPo);
}
