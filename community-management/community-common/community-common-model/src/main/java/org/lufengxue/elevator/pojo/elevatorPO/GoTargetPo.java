package org.lufengxue.elevator.pojo.elevatorPO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  community
 * 包    名:  org.lufengxue.elevator.pojo.elevatorPO
 * 日    期:  2022-04-2022/4/13
 * 时    间:  23:23
 * 描    述: 去目标楼层 电梯PO
 */
@Data
@Table(name="ele_target")
public class GoTargetPo implements Serializable {




    @ApiModelProperty("要去到的楼层号")
    @Column(name = "targetLevels")
    private List<Integer> targetLevels;


    @ApiModelProperty("用户操作：1 关门， 2 开门，3 求救铃")
    @Column(name = "operate")
    private Integer operate;
}
