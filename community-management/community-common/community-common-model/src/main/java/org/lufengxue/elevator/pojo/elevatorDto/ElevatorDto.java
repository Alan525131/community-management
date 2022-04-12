package org.lufengxue.elevator.pojo.elevatorPO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 作 者: 陆奉学
 * 工 程 名:  community
 * 包    名:  org.lufengxue.elevator.pojo.elevatorPO
 * 日    期:  2022-04-2022/4/10
 * 时    间:  0:52
 * 描    述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ele_elevator")
public class ElevatorDto {
    @Id
    @Column(name = "id")
    private Integer id;//楼栋id
    /**
     * //楼栋名字
     */
    @Column(name = "floor_name")
    private String floorName;
    /**
     * //电梯状态 1.等于正常  0 等于非正常
     */
    @Column(name = "elevator_state")
    private String elevatorState;
    /**
     * //目标楼层
     */
    @Column(name = "target_floor")
    private Integer targetFloor;
    /**
     * //用户输入的楼层数(按上下电梯的楼层)
     */
    @Column(name = "floor_number")
    private Integer floorNumber;

    /**
     *  //电梯所在的楼层 默认在一楼
     */
    @Column(name = "floor_level")
    private Integer floorLevel = 1;
    /**
     *  //客户点击的楼层按钮
     */
    @Column(name = "button")
    private Integer button;
    /**
     *  -向上按钮
     */
    @Column(name = "button_up")
    private String upButton;   /**
     *  向下按钮
     */
    @Column(name = "button_next")
    private String nextButton;
    /**
     *  //每层高度
     */
    @Column(name = "elevator_height")
    private Integer elevatorHeight = 3;
    /**
     *  //电梯移动的速度
     */
    @Column(name = "speed")
    private Double speed = 0.5;
    /**
     *  //电梯移动的时间
     */
    @Column(name = "teme")
    private Date teme;
    /**
     *  //用户点击按钮的当前时间
     */
    @Column(name = "current_teme")
    private Date currentTeme;
    /**
     *  //电梯结束时间
     */
    @Column(name = "end_teme")
    private Date endTeme;
    /**
     *  //楼层顶层
     */
    @Column(name = "floor_top")
    private Integer floorTop;
    /**
     *  //楼层最底层
     */
    @Column(name = "floor_bottom")
    private Integer floorBottom;
}
