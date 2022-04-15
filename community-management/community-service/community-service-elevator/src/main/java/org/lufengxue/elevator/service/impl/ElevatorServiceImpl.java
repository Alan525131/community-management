package org.lufengxue.elevator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.lufengxue.elevator.mapper.ElevatorMapper;
import org.lufengxue.elevator.pojo.constant.EleConstant;
import org.lufengxue.elevator.pojo.elevatorDto.CallElevaterDto;
import org.lufengxue.elevator.pojo.elevatorDto.GoTargetDto;
import org.lufengxue.elevator.pojo.elevatorPO.CallElevaterPo;
import org.lufengxue.elevator.pojo.elevatorPO.GoTargetPo;
import org.lufengxue.elevator.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口实现类
 */
@Slf4j
@Service
public class ElevatorServiceImpl implements ElevatorService {

    @Autowired
    private ElevatorMapper elevatorMapper;
    @Autowired
    private ElevatorService elevatorService;


    /**
     * @param targetLevels 目标楼层集合
     *                     根据 客户点击的目标楼层集合 电梯运送客户
     *                     <p>
     *                     思路： 1.首先  建立 一个 目标层集合，用来存储所有楼层，建立上升集合，建立下降集合，判断电梯状态是否正常， 用户 点击上下 按键 ，楼层
     *                     * 2.判断 电梯位置与用户当前位置(对集合进行排序)，电梯上下接用户，获取集合里面的楼层 == 当前楼层 就停下电梯 开门,用户点击 关门，输入目标楼层，
     *                     * 3.根据目标楼层与当前楼层进行判断，是上升还是下降(进行排序)，在根据对应集合(上，下) 与目标楼层集合里面点击的所有楼层 一一对比，对应上了
     *                     * 就停下，开门等操作。若还有目标楼层，则关门，继续进行。(定时每一秒钟打印一次状态 遍历的时候，每到一层选择睡6秒钟)
     */
    @Override
    public GoTargetDto targetElevator(List<Integer> targetLevels) {
        //运行每层电梯所需的时间
        double timeNumber = EleConstant.ELEVATOR_HEIGHR / EleConstant.ELEVATOR_SPEED;
        //查询数据库电梯接用户表 获取电梯状态
        CallElevaterDto callElevaterDto = elevatorService.findButtonSheet("D-1");
        Integer state = callElevaterDto.getElevatorState();
        //查询数据库电梯接用户表 获取电梯接到用户的楼层
        Integer meLevel = elevatorMapper.findMeLevel();
        GoTargetPo targetPo = new GoTargetPo();
        if (targetLevels.size() > 0) {
            // 表示电梯可以正常使用
            if ("00".equals(state)) {
                log.info("状态正常:{}", state);
                //查询电梯接用户表 用户点击的按键
                boolean isDown = elevatorMapper.findIsDown();
                if (isDown) {
                    //把 用户按键：【下】设置回数据库 以待 电梯运送客户接口调用
                    log.info("电梯下降来接客户：======");

                    //获取目标楼层
                    for (int i = 1; i <= targetLevels.size() - 1; i++) {
                        Integer targetFloor = targetLevels.get(i);
                        //获取第一个用户点击的下标
                        Integer first = targetLevels.get(1);
                        //倒序排序
                        targetLevels.sort(Collections.reverseOrder());
                        //遍历 从当前楼层往下遍历
                        for (int j = first - 1; j >= EleConstant.FLOOR_BOTTOM; j--) {
                            timeNumber++;
                            //如果遇见 目标楼层 与运行的电梯楼层相同 开门
                            if (j == targetFloor) {
                                targetPo.setOperate(1);
                                log.info("开门");
                                log.info("关门：" + "重新启动");
                                //去除已经坐过的电梯
//                                meLevel = targetFloor;
                                log.info("到{}层，共用时{}秒", j, timeNumber);
                                targetLevels.remove(targetLevels.indexOf(targetFloor));
                            }
                            log.info("电梯状态：{}，打印时间{}", state, timeNumber);
                            //说明还有楼层没有走完
                            if (targetLevels.size() > 0) {
                                log.info("剩余楼层：" + targetFloor);
                                //获取最后一个下标的元素
                                Integer rear = targetLevels.get(targetLevels.size() - 1);
                                Iterator<Integer> iterator = targetLevels.iterator();
                                while (iterator.hasNext()) {
                                    Integer floor = iterator.next();
                                    log.info("当前层{},打印时间{}", floor, timeNumber);
                                }
                            }
                        }
                    }
                    //表示向上
                } else {
                    //存储用户输入的目标楼层集合
                    for (int i = 1; i <= targetLevels.size() - 1; i++) {
                        //获取用户点击的第一个楼层
                        Integer first = targetLevels.get(1);
                        //获取用户输入的每一个目标楼层
                        Integer targetFloor = targetLevels.get(i);
                        //正序排序
                        Collections.sort(targetLevels);
                        //遍历 从当前楼层往上遍历
                        for (int j = first + 1; j <= EleConstant.FLOOR_TOP; j++) {
                            //计算每一层所用的时间 秒为单位
                            timeNumber++;
                            //如果遇见 目标楼层 与运行的电梯楼层相同 开门
                            if (j == targetFloor) {
                                targetPo.setOperate(1);
                                log.info("开门");
                                log.info("关门：" + "重新启动");
                                //去除已经坐过的电梯
//                                meLevel = targetFloor;
                                log.info("到{}层，共用时{}秒", j, timeNumber);
                                targetLevels.remove(targetLevels.indexOf(targetFloor));
                            }
                            log.info("电梯状态：{}，打印时间{}", state, timeNumber);
                            //说明还有楼层没有走完
                            if (targetLevels.size() > 0) {
                                log.info("剩余楼层：" + targetFloor);
                                //获取最后一个下标的元素
                                Integer rear = targetLevels.get(targetLevels.size() - 1);
                                Iterator<Integer> iterator = targetLevels.iterator();
                                while (iterator.hasNext()) {
                                    Integer floor = iterator.next();
                                    log.info("当前层{},打印时间{}", floor, timeNumber);
                                }
                            }
                        }
                    }
                }
            } else if ("11".equals(state)) {
                throw new RuntimeException("电梯状态异常");
            }
        }

        return elevatorMapper.targetElevator( targetLevels);
    }

    /**
     * @param meLevel 用户当前楼层
     *                用户按键电梯上下 ，true：下，false：上；
     *                根据用户 按键的楼层，和按键的上下 ，电梯来接用户
     */
    @Override
    public CallElevaterDto callElevator(Integer meLevel, Boolean isDown) {
        //查询数据库 获取 电梯状态
        CallElevaterDto callElevaterDto = elevatorService.findButtonSheet("D_1");
        Integer state = callElevaterDto.getElevatorState();
        //查询数据库 获取 电梯所在楼层
        Integer liftFloor = callElevaterDto.getLiftFloor();
        //获取电梯接用户表对象
        CallElevaterPo callElevaterPo = new CallElevaterPo();
        // 表示电梯可以正常使用
        if ("00".equals(state)) {
            //表示向下
            if (isDown) {
                //把 用户按键：【下】设置回数据库 以待 电梯运送客户接口调用
                callElevaterPo.setIsDown(true);
                log.info("电梯下降来接客户：======");
                //表示向上
            }
            if (!isDown) {
                //把 用户按键：【上】设置回数据库 以待 电梯运送客户接口调用
                callElevaterPo.setIsDown(false);
                log.info("电梯上升来接客户：======");
                //如果电梯所在楼层 小于用户所在楼层
            }
            if (liftFloor < meLevel) {
                //楼栋电梯楼层 电梯往上走 到用户楼层接用户
                for (int i = liftFloor + 1; i <= meLevel; i++) {
                    log.info("到达第{}层", i);
                }
                //如果电梯所在楼层高于 用户所在楼层 电梯往下走
            } else if (liftFloor > meLevel) {
                for (int j = EleConstant.FLOOR_BOTTOM - 1; j >= meLevel; j--) {
                    log.info("到达第{}层", j);
                }
            }
            //层序走到这里 证明电梯已经到达用户楼层
            log.info("电梯到达用户楼层{}", meLevel);
        } else if ("11".equals(state)){
            throw new RuntimeException("电梯状态异常");
        }
        return elevatorMapper.callElevator(meLevel);
    }

    /**
     *  查询电梯状态
     *  电梯状态 00.等于正常  11等于非正常
     * @return
     */
    @Override
    public CallElevaterDto findButtonSheet( String floorName) {
        return elevatorMapper.findButtonSheet(floorName);
    }
}
