package org.lufengxue.elevator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lufengxue.elevator.mapper.ElevatorMapper;
import org.lufengxue.elevator.pojo.elevatorPO.Elevator;
import org.lufengxue.elevator.pojo.elevatorPO.Floor;
import org.lufengxue.elevator.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


    /**
     * @param floorName 大楼名字
     *                  根据大楼名字 查询所有本大楼的楼层号
     */
    @Override
    public List<Floor> findFloor(String floorName) {

        return elevatorMapper.findFloor(floorName);
    }

    /**
     * @param floorName   大楼名称
     * @param buttons     电梯上下按钮
     * @param floorNumber 当前楼层号
     * @return 根据当前大楼名字, 与用户当前楼层号, 电梯上下按钮来进行调度电梯接用户
     */
    @Override
    public List<Floor> callElevator(String floorName, String buttons, Integer floorNumber) {
        //非空判断
        if (floorNumber > 0 && floorNumber <= 20 && StringUtils.isNotBlank(floorName) && StringUtils.isNotBlank(buttons)) {
            //1.根据楼层号查询出楼层每层高度,楼层状态
            Floor floor = elevatorMapper.queryFloor(floorNumber, floorName);
            //楼层 状态 1,最低楼  2,中间楼   3最高楼
            Integer floorStatus = floor.getFloorStatus();
            //每层楼的高度
            Integer floorHeight = floor.getFloorHeight();
            // 2.查询 电梯表中的电梯速度 与电梯所在楼层
            Elevator elevatorList = elevatorMapper.queryElevator();
            //电梯所在楼层位置
            Integer inFloor = elevatorList.getInFloor();
            //电梯运行速度: 秒/米
            Double speed = elevatorList.getSpeed();

            //获取每层耗时多少秒 高度 除以 每秒运行速度米 todo 不知道为什么在循环的时候不可以 += time;
            Double timeCount = floorHeight / speed;
            //判断楼层状态 最底层
            if (floorStatus == 1) {
                //2.根据用户传入的上下,楼层状态 判断电梯 与楼层的距离 进行遍历
                if ("上".equals(buttons)) {
                    log.info("您在第一楼层{},电梯往上运行至{}层:=======开门", floorNumber, inFloor);

                }
                //最高层
            } else if (floorStatus == 3) {
                if ("下".equals(buttons)) {
                    log.info("您在最高楼层{},电梯往下运行至{}层: =======开门", floorNumber, inFloor);
                }
                //中间层
            } else if (floorStatus == 2) {
                log.info("您在最高楼层{},电梯往下运行至{}层: =======开门", floorNumber, inFloor);
            } else {
                log.info("您点击的选择按钮有误,请重新点击");
            }
            //用户当前所在楼层 大于 电梯当前所在楼层 电梯往上遍历
            if (floorNumber > inFloor) {
                for (int i = inFloor + 1; i <= floorNumber; i++) {
                    timeCount++;
                    inFloor = i;
                    log.info("您在第{}楼层,电梯往上运行至{}层,耗时{}秒", floorNumber, inFloor, timeCount);
                }
                //用户当前所在楼层 小于 电梯当前所在楼层 电梯往下遍历
            } else if (floorNumber < inFloor) {
                for (int i = inFloor - 1; i >= floorNumber; i--) {
                    timeCount++;
                    inFloor = i;
                    log.info("您在第{}楼层,电梯往下运行至{}层,耗时{}秒", floorNumber, inFloor, timeCount);
                }
            }
            log.info("开门大吉============================");
            elevatorList.setInFloor(inFloor);
            //每次电梯停下之后,把电梯位置设置回电梯表中
//            elevatorMapper.updateInFloor(inFloor);  todo  更新
        }
        return elevatorMapper.callElevator(floorName, floorNumber, buttons);
    }

    /**
     * 根据用户输入的目标楼层运行电梯接送用户到目的地
     *
     * @param status       电梯使用状态: 1 可用，2不可用
     * @param floorButtons 楼层按钮 目标楼层集合
     * @param sports       电梯运行状态：1 往上，2往下，3静止
     * @return
     */
    @Override
    public List<Elevator> runElevator(Elevator elevator) {
        Integer inFloor = elevator.getInFloor();
        Set<Integer> floorButtons = elevator.getFloorButtons();
        Integer status = elevator.getStatus();
        Integer sports = elevator.getSports();
        //非空判断
        if (status > 0 && status < 3 && sports > 0 && sports < 4 && !floorButtons.isEmpty()) {

            List<Floor> floorList = elevatorMapper.findFloor("d栋");
            //因为 set 集合是无序的 在 循环没有取完的情况下  会照成迭代去除数据是无序的 所以转成 list
            List<Integer> collect = floorButtons.stream().distinct().collect(Collectors.toList());
            //电梯可用状态
            if (status == 1) {
                log.info("电梯使用正常,用户敬请使用");
                // 1 == 电梯运行状态  往上
                if (sports == 1) {
                    log.info("电梯运行状态：{} 往上状态，", sports);
                    //查询楼层 遍历楼层 正序
                    // todo 怎么把用户呼叫电梯接口之后的结果传到这里   获取用户所在楼层
                    //正序排序
                    Collections.sort(collect);
                    //获取楼层
                    //遍历用户所在大楼的所有楼层 往上 就正序排序
                    if (collect.size() > 0) {
                        for (int i = inFloor + 1; i < floorList.size() - 1; i++) {
                            if (collect.contains(i)) {
                                inFloor = i;
                                log.info("开门:电梯往上运行至{}层", inFloor);
                                collect.remove(collect.indexOf(i));
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException("抱歉电梯出现故障");
                                }
                                log.info("关门继续运行");
                            }
                            inFloor = i;
                            log.info("电梯往上运行至{}层", inFloor);
                        }
                        //如果大于0 证明目标集合中还有目标楼层
                        if (collect.size() > 0) {
                            Iterator<Integer> iterator = floorButtons.iterator();
                            inFloor = iterator.next();
                        }
                        log.info("电梯运行完所有上升楼层" + inFloor);
                    }

                    //电梯运行状态  往下
                } else if (sports == 2) {
                    //降序排序
                    collect.sort(Collections.reverseOrder());
                    if (collect.size() > 0) {
                        for (int i = inFloor - 1; i > 1; i--) {
                            if (collect.contains(i)) {
                                collect.remove(i);
                                log.info("停下开门:电梯往上运行至{}层,", inFloor);
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException("抱歉电梯出现故障");
                                }
                                log.info("关门继续运行");
                            }
                            inFloor = i;
                            log.info("电梯往上运行至{}层", inFloor);
                        }
                        //如果大于0 证明目标集合中还有目标楼层
                        if (collect.size() > 0) {
                            Iterator<Integer> iterator = floorButtons.iterator();
                            Integer floor = iterator.next();
                        }
                        log.info("电梯运行完所有下行楼层");
                    }
                    log.info("电梯运行状态： {}往下状态，", sports);
                    // 电梯运行状态 禁止 状态
                } else {
                    log.info("电梯运行状态： {}禁止状态，", sports);
                }
                //status == 2 电梯不可用
            } else {
                throw new RuntimeException("抱歉电梯正在维修当中");
            }
        }
        return null;
    }
}
