package org.lufengxue.elevator.service.impl;

import org.lufengxue.core.service.impl.CoreServiceImpl;
import org.lufengxue.elevator.mapper.ElevatorMapper;
import org.lufengxue.elevator.pojo.elevatorPO.ElevatorDto;
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
     * @return  思路： 1.首先  建立 一个 目标层集合，用来存储所有楼层，建立上升集合，建立下降集合，判断电梯状态是否正常， 用户 点击上下 按键 ，楼层
     *      2.判断 电梯位置与用户当前位置(对集合进行排序)，电梯上下接用户，获取集合里面的楼层 == 当前楼层 就停下电梯 开门,用户点击 关门，输入目标楼层，
     *      3.根据目标楼层与当前楼层进行判断，是上升还是下降(进行排序)，在根据对应集合(上，下) 与目标楼层集合里面点击的所有楼层 一一对比，对应上了
     *      就停下，开门等操作。若还有目标楼层，则关门，继续进行。(定时每一秒钟打印一次状态 遍历的时候，每到一层选择睡6秒钟)
     *
     */
    @Override
    public ElevatorDto buttonElevator(ElevatorPo elevatorPo)  {
        //电梯里 你点击 所要去的楼层按钮 表示
        //楼层高度
        int elevatorHeight = elevatorPo.getElevatorHeight();
        //集合 存储目标楼层
        ArrayList<Integer> heightList = new ArrayList<>();
        //集合 电梯向上集合
        ArrayList<Integer> upList = new ArrayList<Integer>();
        //集合  电梯向下集合
        ArrayList<Integer> nextList = new ArrayList<Integer>();

        //遍历楼层 把所有楼层存储进目标楼层
        for (int i = 1; i <= elevatorHeight; i++) {
            upList.add(i);
            nextList.add(i);
        }
        //电梯运行状态
        Integer elevatorState = Integer.valueOf(elevatorPo.getElevatorState());
        if (elevatorState == 1){
            heightList.add(5);
            //获取电梯的楼层
            Integer floor = elevatorPo.getFloorLevel();
            //电梯口所在楼层小于当前用户所在楼层，则电梯网上
            if (floor < 5){
                //排序
                Collections.sort(upList);
                System.out.println("电梯来上升来接客户：======");
                for (int i = floor + 1 ; i <= upList.size() - 1 ; i++) {
                    Integer number = upList.get(i);
                    try {
                        Thread.sleep(1000);
                        System.out.println("当前的状态：" + elevatorState);
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("到达第：" + number + " 层");
                    if(i == 5){
                        System.out.println("电梯到达接客户的位置：" + number + "层");
                    }
                }
            }
        }else {
            throw new RuntimeException("电梯不正常");
        }
        return null;
    }

    /**
     *
//     * @param upButton 向下按钮
//     * @param nextButton 向上
//     * @param floorNumber 楼层
     */
    @Override
    public ElevatorDto clickButton(ElevatorPo elevatorPo) {
        //集合 存储目标楼层
        ArrayList<Integer> heightList = new ArrayList<>();

        //遍历楼层 把所有楼层存储进目标楼层
        for (int i = 1; i <= elevatorPo.getElevatorHeight(); i++) {
            heightList.add(i);
        }
        //1.获取客户点击 的 上下箭头
        if("向上".equals(elevatorPo.getUpButton())){
            elevatorPo.setUpButton("向上");
            System.out.println("客户点击的是向上按钮");

        }else if ("向下".equals(elevatorPo.getNextButton())){
            elevatorPo.setNextButton("向下");
            System.out.println("客户点击的是向下按钮");
        }
        //2.获取电梯的楼层
        Integer floor = elevatorPo.getFloorLevel();
        if (floor < elevatorPo.getFloorNumber()){
            //正序排序
            Collections.sort(heightList);
            System.out.println("电梯上升来接客户：======");
        }else if (floor > elevatorPo.getFloorNumber()){
            //倒序排序
            heightList.sort(Collections.reverseOrder());
            System.out.println("电梯下降来接客户：======");
        }
        for (int i = floor + 1 ; i <= heightList.size() - 1 ; i++){
            //获取所到的没有一个楼层
            System.out.println("到达第：" + i + " 层");
            if (heightList.contains(i)){
                elevatorPo.setFloorOpen("开门");
                System.out.println("开门=======================");
            }
        }
        return elevatorMapper.clickButton(elevatorPo);
    }


}
