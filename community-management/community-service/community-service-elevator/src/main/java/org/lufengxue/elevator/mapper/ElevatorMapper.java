package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lufengxue.elevator.pojo.elevatorDto.CallElevaterDto;
import org.lufengxue.elevator.pojo.elevatorDto.GoTargetDto;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
@Mapper
public interface ElevatorMapper  {
//    @Select("SELECT floor_name, current_teme, end_teme,target_floor,floor_number,elevator_height,speed  FROM `ele_elevator` ")
//    ElevatorDto clickButton(ElevatorPo elevatorPo);
//
////    @Update("update ele_elevator set" +
////            " floor_name =#{floorName},elevator_state =#{elevatorState},button_up =#{upButton},button_next =#{nextButton}，floor_level =#{floorLevel},button =#{button}," +
////            "elevator_height =#{elevatorHeight}, current_teme =#{currentTeme},speed =#{speed},teme =#{teme},current_teme =#{currentTeme}, end_teme =#{endTeme}," +
////            "floor_top =#{floorTop},floor_bottom =#{floorBottom},target_floor =#{targetFloor},floor_number =#{floorNumber},floor_open =#{floorOpen},floor_off =#{floorOff} where id =#{id}")
//
//    @Select("SELECT floor_name, current_teme, end_teme,target_floor,floor_number,elevator_height,speed  FROM `ele_elevator` ")
//    ElevatorDto buttonElevator(ElevatorPo elevatorPo);


    CallElevaterDto findButtonSheet(String floorName);


    GoTargetDto targetElevator(List<Integer> targetLevels);

    CallElevaterDto updateCallElevator(String floorName);
}
