package org.lufengxue.elevator.service.impl;

import org.lufengxue.core.service.impl.CoreServiceImpl;
import org.lufengxue.elevator.dao.ElevatorMapper;
import org.lufengxue.elevator.pojo.elevatorDto.ElevatorDto;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorPo;
import org.lufengxue.elevator.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口实现类
 */
@Service
public class ElevatorServiceImpl extends CoreServiceImpl<ElevatorPo> implements ElevatorService {

    private ElevatorMapper elevatorMapper;

    @Autowired
    public ElevatorServiceImpl(ElevatorMapper elevatorMapper) {
        super(elevatorMapper, ElevatorPo.class);
        this.elevatorMapper = elevatorMapper;
    }



    /**
     *  电梯运行管理  todo 没有 完成
     * @param
     * @return
     */
    @Override
    public ElevatorDto buttonElevator(ElevatorPo elevatorPo) {
        //电梯里 你点击 所要去的楼层按钮 表示
        Integer button = elevatorPo.getButton();
        //楼层高度
        int elevatorHeight = elevatorPo.getElevatorHeight();
        //集合 楼层的高度
        ArrayList<Integer> heightList = new ArrayList<>();
        //集合 电梯向上集合
        Integer buttonUp = elevatorPo.getButtonUp();
        ArrayList<Integer> buttonUpList = new ArrayList<Integer>();
        //集合  电梯向下集合
        Integer buttonNext = elevatorPo.getButtonNext();
        ArrayList<Integer> buttonNextList = new ArrayList<Integer>();

        for (int i = 1; i <= elevatorHeight; i++) {
            heightList.add(i);
        }
        Integer elevatorState = Integer.valueOf(elevatorPo.getElevatorState()); //电梯运行状态
        if (elevatorState == 1){
            // 1. 向上按钮
            if (buttonUp.equals(1)){
                //获取当前电梯的所在的楼层
                Integer floorLevel = elevatorPo.getFloorLevel();
                if(button> floorLevel){
                    buttonUpList.add(button);
                    Collections.sort(buttonUpList);
                }
                //向下按钮
            }else if(buttonNext.equals(0)){
                    buttonNextList.add(elevatorState);
            }
        }else {
            throw new RuntimeException("电梯状态非正常运行");
        }
        elevatorPo.setFloorLevel(buttonNext);
        return null;
    }
}
