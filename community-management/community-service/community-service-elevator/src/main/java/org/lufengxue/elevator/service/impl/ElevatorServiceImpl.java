package org.lufengxue.elevator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lufengxue.core.service.impl.CoreServiceImpl;
import org.lufengxue.elevator.mapper.ElevatorMapper;
import org.lufengxue.elevator.pojo.constant.Constant;
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
@Slf4j
@Service
public class ElevatorServiceImpl extends CoreServiceImpl<ElevatorPo> implements ElevatorService {

    private ElevatorMapper elevatorMapper;

    @Autowired
    public ElevatorServiceImpl(ElevatorMapper elevatorMapper) {
        super(elevatorMapper, ElevatorPo.class);
        this.elevatorMapper = elevatorMapper;
    }


    /**
     * 电梯运行管理  todo 没有 完成
     *
     * @param
     * @return 思路： 1.首先  建立 一个 目标层集合，用来存储所有楼层，建立上升集合，建立下降集合，判断电梯状态是否正常， 用户 点击上下 按键 ，楼层
     * 2.判断 电梯位置与用户当前位置(对集合进行排序)，电梯上下接用户，获取集合里面的楼层 == 当前楼层 就停下电梯 开门,用户点击 关门，输入目标楼层，
     * 3.根据目标楼层与当前楼层进行判断，是上升还是下降(进行排序)，在根据对应集合(上，下) 与目标楼层集合里面点击的所有楼层 一一对比，对应上了
     * 就停下，开门等操作。若还有目标楼层，则关门，继续进行。(定时每一秒钟打印一次状态 遍历的时候，每到一层选择睡6秒钟)
     */
    @Override
    public ElevatorDto buttonElevator(ElevatorPo elevatorPo) {
        //电梯移动的速度 每秒 0.5 米
        double speed = elevatorPo.getSpeed();
        //每层高度 3米
        double elevatorHeight = elevatorPo.getElevatorHeight();
        //获取每一层的用时 时间
        Double temeNnumber = elevatorHeight / speed;
        //2.获取电梯的楼层
        Integer floor = elevatorPo.getFloorLevel();
        //判断电梯的状态
        String state = elevatorPo.getElevatorState();
        //获取客户点击的所有楼层
        Integer[] button = elevatorPo.getButton();

        //1.集合 存储电梯的所有楼层
        ArrayList<Integer> targetList = new ArrayList<>();
        //遍历楼层 把所有楼层存储进集合进行遍历
        for (int i = 1; i <= Constant.FLOOR_TOP; i++) {
            targetList.add(i);
        }
        //3.判断电梯的状态  定时打印状态
        if ("1".equals(state)) {
//            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            // 参数：1、任务体 2、首次执行的延时时间
            //      3、任务执行间隔 4、间隔时间单位
//            service.scheduleAtFixedRate(() -> System.out.println("电梯状态" + state + " 现在时间" + new Date()), 0, 1, TimeUnit.SECONDS);
            log.info("状态正常:{}", state);
            //4.获取所有被用户点击的楼层 进行判断
            for (int c = 0; c < button.length; c++) {
                Integer number = button[c];
                if (floor < number) {
                    //如果 当前电梯的楼层小于 你要去的第一个楼层就是正序
                    Collections.sort(targetList);
                    log.info("电梯带着客户上升：======");
                    //5.遍历电梯集合 判断是否包含有客户点击的电梯号   电梯上升
                    for (int j = floor + 1; j < targetList.size() - 1; j++) {
                        temeNnumber++;
                        log.info("到{}层，共用时{}秒", j, temeNnumber);
                        if (targetList.contains(number)) {
                            log.info("道达客户第{}楼层：开门========共用时{}秒", j, temeNnumber);
                            elevatorPo.setFloorOpen("开门");
                            log.info("关门");
                        }
                        log.info("电梯状态：{}，打印时间{}", state, temeNnumber);
                    }
                } else if (floor > number) {
                    //如果 当前电梯的楼层大于 你要去的第一个楼层就是倒序 下行
                    targetList.sort(Collections.reverseOrder());
                    log.info("电梯带着客户下降：======");
                    //5.遍历电梯集合 判断是否包含有客户点击的电梯号   电梯下降
                    for (int j = floor - 1; j >= number; j--) {
                        temeNnumber++;
                        log.info("到{}层，共用时{}秒", j, temeNnumber);
                        if (targetList.contains(number)) {
                            // todo  "floorLevel":8,  "button":[3,4,6,8,9,15,14]  但是到6楼没有停下来 开门
                            if (j == number ){
                                elevatorPo.setFloorOpen("开门");
                                log.info("电梯状态：{}，道达客户第{}楼层,开门========共用时{}秒",state, j, temeNnumber);
                                log.info("关门");
                            }
                            floor = j;
                            //去掉冗余数据
                            targetList.remove(targetList.indexOf(j));
                            log.info("电梯状态：{}，打印时间{}", state, temeNnumber);
                        }
                    }
                }

            }
        } else if ("0".equals(state)) {
            throw new RuntimeException("电梯异常");
        }
        return elevatorMapper.buttonElevator(elevatorPo);
    }

    /**
     * 根据用户点击的上下按键
     * 电梯来接用户
     */
    @Override
    public ElevatorDto clickButton(ElevatorPo elevatorPo) {
        //集合 存储目标楼层
        ArrayList<Integer> targetList = new ArrayList<>();
        //遍历楼层 把所有楼层存储进目标楼层
        for (int i = 1; i <= Constant.FLOOR_TOP; i++) {
            targetList.add(i);
        }
        //1.获取客户点击 的 上下箭头
        if ("向上".equals(elevatorPo.getUpButton())) {
            elevatorPo.setUpButton("向上");
            log.info("客户点击的是向上按钮");

        } else if ("向下".equals(elevatorPo.getNextButton())) {
            elevatorPo.setNextButton("向下");
            log.info("客户点击的是向下按钮");
        }
        //2.获取电梯的楼层
        Integer floor = elevatorPo.getFloorLevel();
        Integer floorNumber = elevatorPo.getFloorNumber();
        if (floor < floorNumber) {
            //正序排序
            Collections.sort(targetList);
            log.info("电梯上升来接客户：======");
        } else if (floor > floorNumber) {
            //倒序排序
            targetList.sort(Collections.reverseOrder());
            log.info("电梯下降来接客户：======");
        }
        for (int i = floor + 1; i <= targetList.size() - 1; i++) {
            //获取所到的每一个楼层 看目标集合里面是否有你传入的 楼层
            if (i == floorNumber) {
                log.info("到达第{}层", i);
//                heightList.remove(heightList.indexOf(i));
                log.info("开门=======================");
                elevatorPo.setFloorOpen("开门");
                log.info("关门");
            }
        }
        return elevatorMapper.clickButton(elevatorPo);
    }


}
