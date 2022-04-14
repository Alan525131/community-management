package org.lufengxue.elevator.pojo.elevatorDto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  community
 * 包    名:  org.lufengxue.elevator.pojo.elevatorPO
 * 日    期:  2022-04-2022/4/13
 * 时    间:  23:23
 * 描    述: 去目标楼层 电梯DtO
 */
@Data
@Table(name="ele_target")
public class GoTargetDto implements Serializable {

    /**
     * 电梯运行表 ID
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     *  要去到的楼层号
     */
    @Column(name = "targetLevels")
    private List<Integer> targetLevels;

    /**
     * 电梯状态 00.等于正常  11等于非正常
     */
    @Column(name = "elevator_state")
    private Integer elevatorState;

    /**
     * 电梯状态运行公用时长
     */
    @Column(name = "total_time")
    private Integer totalTime;

}
