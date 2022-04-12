package org.lufengxue.elevator.mapper;

import org.apache.ibatis.annotations.Select;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;
import tk.mybatis.mapper.common.Mapper;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： ElevatorDao
 */
public interface ElevatorMapper extends Mapper<ElevatorPo> {
    @Select("SELECT * FROM ele_elevator WHERE floor_name = zhangsan")
    ElevatorDto clickButton(ElevatorPo elevatorPo);

}
