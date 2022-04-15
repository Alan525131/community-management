package org.lufengxue.elevator.pojo.elevatorPO;

import lombok.Data;

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
@Table(name="ele_call")
public class CallElevaterPo implements Serializable {

    /**
     * 电梯按键表 ID
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     *  用户所在楼层数
     */
    @Column(name = "floor_meLevel")
    private Integer meLevel;

    /**
     * 用户按键电梯上下 ，true：下，false：上；
     *
     */
    @Column(name = "isDown")
    private Boolean isDown;

    /**
     * //楼栋名字
     */
    @Column(name = "floor_name")
    private String floorName;

}
