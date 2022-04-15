package org.lufengxue.elevator.pojo.elevatorDto;

import io.swagger.annotations.ApiModelProperty;
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
 * 描    述: 电梯外面 按键Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ele_call")
public class CallElevaterDto implements Serializable {


    @Id
    @ApiModelProperty("电梯按键表 ID")
    @Column(name = "id")
    private Integer id;


    @ApiModelProperty("楼栋名字")
    @Column(name = "floor_name")
    private String floorName;


    @Column(name = "floor_meLevel")
    @ApiModelProperty("用户所在楼层数")
    private Integer meLevel;

    @ApiModelProperty("用户所在电梯状态")
    @Column(name = "elevator_state")
    private String elevatorState;


    @ApiModelProperty("电梯所在楼层")
    @Column(name = "liftFloor")
    private Integer liftFloor;


    @ApiModelProperty("用户按键电梯上下 ，true：下，false：上；")
    @Column(name = "isDown")
    private Boolean isDown;

}
