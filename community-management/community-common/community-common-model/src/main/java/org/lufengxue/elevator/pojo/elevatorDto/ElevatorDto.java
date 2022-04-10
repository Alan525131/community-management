package org.lufengxue.elevator.pojo.elevatorDto;

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
     * //向上按钮
     */
    @Column(name = "button_up")
    private Integer buttonUp;
    /**
     * //向下按钮
     */
    @Column(name = "button_next")
    private Integer buttonNext;
    /**
     *  //楼栋当前楼层
     */
    @Column(name = "floor_level")
    private Integer floorLevel = 1;
    /**
     *  //客户点击的楼层按钮
     */
    @Column(name = "button")
    private Integer button;
    /**
     *  //每层高度
     */
    @Column(name = "elevator_height")
    private Integer elevatorHeight;
    /**
     *  //电梯移动的速度
     */
    @Column(name = "speed")
    private Double speed;
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



    //get方法
    public Integer getId() {
        return id;
    }

    //set方法
    public void setId(Integer id) {
        this.id = id;
    }
    //get方法
    public String getFloorName() {
        return floorName;
    }

    //set方法
    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
    //get方法
    public String getElevatorState() {
        return elevatorState;
    }

    //set方法
    public void setElevatorState(String elevatorState) {
        this.elevatorState = elevatorState;
    }
    //get方法
    public Integer getButtonUp() {
        return buttonUp;
    }

    //set方法
    public void setButtonUp(Integer buttonUp) {
        this.buttonUp = buttonUp;
    }
    //get方法
    public Integer getButtonNext() {
        return buttonNext;
    }

    //set方法
    public void setButtonNext(Integer buttonNext) {
        this.buttonNext = buttonNext;
    }
    //get方法
    public Integer getFloorLevel() {
        return floorLevel;
    }

    //set方法
    public void setFloorLevel(Integer floorLevel) {
        this.floorLevel = floorLevel;
    }
    //get方法
    public Integer getButton() {
        return button;
    }

    //set方法
    public void setButton(Integer button) {
        this.button = button;
    }
    //get方法
    public Integer getElevatorHeight() {
        return elevatorHeight;
    }

    //set方法
    public void setElevatorHeight(Integer elevatorHeight) {
        this.elevatorHeight = elevatorHeight;
    }
    //get方法
    public Double getSpeed() {
        return speed;
    }

    //set方法
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
    //get方法
    public Date getTeme() {
        return teme;
    }

    //set方法
    public void setTeme(Date teme) {
        this.teme = teme;
    }
    //get方法
    public Date getCurrentTeme() {
        return currentTeme;
    }

    //set方法
    public void setCurrentTeme(Date currentTeme) {
        this.currentTeme = currentTeme;
    }
    //get方法
    public Date getEndTeme() {
        return endTeme;
    }

    //set方法
    public void setEndTeme(Date endTeme) {
        this.endTeme = endTeme;
    }
    //get方法
    public Integer getFloorTop() {
        return floorTop;
    }

    //set方法
    public void setFloorTop(Integer floorTop) {
        this.floorTop = floorTop;
    }
    //get方法
    public Integer getFloorBottom() {
        return floorBottom;
    }

    //set方法
    public void setFloorBottom(Integer floorBottom) {
        this.floorBottom = floorBottom;
    }


}
