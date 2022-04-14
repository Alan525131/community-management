package org.lufengxue.elevator.pojo.elevatorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 作 者: 陆奉学
 * 工 程 名:  community
 * 包    名:  org.lufengxue.elevator.pojo.elevatorPO
 * 日    期:  2022-04-2022/4/13
 * 时    间:  23:38
 * 描    述: 电梯外面 按键PO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ele_call")
public class CallElevaterDto implements Serializable {

    /**
     * 电梯按键表 ID
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * //楼栋名字
     */
    @Column(name = "floor_name")
    private String floorName;
    /**
     *  用户所在楼层数
     */
    @Column(name = "floor_meLevel")
    private Integer meLevel;/**
     *  用户所在电梯状态
     */
    @Column(name = "elevator_state")
    private Integer elevatorState;
    /**
     *  电梯所在楼层
     */
    @Column(name = "liftFloor")
    private Integer liftFloor;


}
