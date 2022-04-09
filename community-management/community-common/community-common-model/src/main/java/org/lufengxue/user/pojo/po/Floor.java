package org.lufengxue.user.pojo.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Floor构建
 */
@Table(name="ele_floor")
public class Floor implements Serializable{

	@Id
    @Column(name = "id")
	private Integer id;//楼栋id

    @Column(name = "floor_name")
	private String floorName;//楼栋名字

    @Column(name = "elevator_state")
	private String elevatorState;//电梯状态

    @Column(name = "button_up")
	private Integer buttonUp;//向上按钮

    @Column(name = "button_next")
	private Integer buttonNext;//向下按钮

    @Column(name = "floor_level")
	private Integer floorLevel;//楼栋楼层

    @Column(name = "button")
	private Integer button;//客户点击的楼层按钮

    @Column(name = "elevator_height")
	private Integer elevatorHeight;//每层高度

    @Column(name = "speed")
	private Double speed;//电梯移动的速度

    @Column(name = "teme")
	private Date teme;//电梯移动的时间

    @Column(name = "current_teme")
	private Date currentTeme;//用户点击按钮的当前时间

    @Column(name = "end_teme")
	private Date endTeme;//电梯结束时间

    @Column(name = "floor_top")
	private Integer floorTop;//楼层顶层

    @Column(name = "floor_bottom")
	private Integer floorBottom;//楼层底层



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
