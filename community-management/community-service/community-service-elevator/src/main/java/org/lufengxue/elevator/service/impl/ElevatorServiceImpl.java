package org.lufengxue.elevator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.lufengxue.elevator.mapper.ElevatorMapper;
import org.lufengxue.constant.EleConstant;
import org.lufengxue.pojo.elevator.elevatorDto.Elevator;
import org.lufengxue.pojo.elevator.elevatorDto.Floor;
import org.lufengxue.elevator.service.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 描述    ： Elevator业务接口实现类
 */
@Slf4j
@Service
public class ElevatorServiceImpl implements ElevatorService {
    /**
     * 计算出每层耗时多少秒 高度 除以 每秒运行速度米
     */
    Double timeCount = EleConstant.ELEVATOR_HEIGHR / EleConstant.ELEVATOR_SPEED;

    @Autowired
    private ElevatorMapper elevatorMapper;

    /**
     * @param floorName 大楼名字
     *                  根据大楼名字 查询所有本大楼的楼层号
     */
    @Override
    public List<Floor> findFloor(String floorName) {
        int i = 1 / 0;

        return elevatorMapper.findFloor(floorName);
    }

    /**
     * @param floorName   大楼名称
     * @param buttons     电梯上下按钮
     * @param floorNumber 当前楼层号
     * @return 根据当前大楼名字, 与用户当前楼层号, 电梯上下按钮来进行调度电梯接用户
     */
    @Override
    public Floor callElevator(String floorName, String buttons, Integer floorNumber) {
        if (StringUtils.isEmpty(floorName)) {
            throw new RuntimeException("您输入的大楼名称不正确");
        }
        if (!("上".equals(buttons) || "下".equals(buttons))) {
            throw new RuntimeException("您输入的电梯按钮不正确");
        }
        if (floorNumber == null || floorNumber <= 0) {
            throw new RuntimeException("您输入的楼层号不成在");
        }
        //获取当栋大楼的楼层号,判断楼层号的范围
        List<Floor> floors = elevatorMapper.findFloor(floorName);
        //获取对应楼层对象
        Floor floor = getFloorObject(floors, floorNumber);
        //获取楼层状态 1,最低楼  2,中间楼   3最高楼
        assert floor != null;
        Integer floorStatus = floor.getFloorStatus();
        //如果是在一楼 ,不允许点击往下按钮
        if (floorStatus == 1 && "下".equals(buttons)) {
            throw new RuntimeException("您输入的楼层按钮不正确");
            //如果是在最高楼 ,不允许点击往上按钮
        } else if (floorStatus == 3 && "上".equals(buttons)) {
            throw new RuntimeException("您输入的楼层按钮不正确");
        }
        //根据当前大楼名称查询出所有对应的电梯数据
        List<Elevator> elevatorList = elevatorMapper.findElevator(floorName);
        //获取距离最近的电梯对象
        Elevator elevator = getElevatorObject(elevatorList, floorNumber, buttons);
        assert elevator != null;
        //获取电梯楼层在什么地方
        Integer inFloor = elevator.getInFloor();
        //楼层的状态  1最底层, 2中间层  3最高层
        if (floorStatus == 2 || floorStatus == 1 || floorStatus == 3) {
            //正常情况下,如果用户是往上按钮
            if ("上".equals(buttons)) {
                //呼叫电梯往上运行
                callElevatorUP(floorNumber, inFloor, floors, elevator);
                //正常情况下,如果是往下按钮
            } else {
                //呼叫电梯往下运行
                callElevatorDown(floorNumber, inFloor, floors, elevator);
            }

        }
        System.out.println("现在电梯楼层在哪一层:" + inFloor);
        //根据当前电梯id,获取对应电梯数据返回
        Integer id = elevator.getId();
        return elevatorMapper.callElevator(id);
    }

    /**
     * 根据用户输入的目标楼层运行电梯接送用户到目的地
     *
     * @param floorButtons 楼层按钮 目标楼层集合
     * @param id           电梯id
     * @return
     */
    @Override
    public List<Elevator> runElevator(Set<Integer> floorButtons, Integer id) {
        //非空判断
        if (CollectionUtils.isEmpty(floorButtons) || id == null || id <= 0) {
            throw new RuntimeException("floorButtons参数不可为空");
        }
        //根据电梯id 查询电梯列表获取对应的属性,
        Elevator elevator = elevatorMapper.queryElevator(id);
        //status 电梯使用状态: 1 可用，2不可用
        Integer status = elevator.getStatus();
        //sports 电梯运行状态：1 往上，2往下，3静止
        Integer sports = elevator.getSports();
        //获取电梯所在楼层
        Integer inFloor = elevator.getInFloor();
        if (inFloor == null || inFloor <= 0) {
            throw new RuntimeException("电梯楼层错误");
        }
        if (status == null || status < 0 || status > 2) {
            throw new RuntimeException("抱歉status参数不正确");
        }
        if (sports == null || sports < 0 || sports > 3) {
            throw new RuntimeException("抱歉sports参数不正确");
        }
        //电梯不可用状态
        if (status != 1) {
            throw new RuntimeException("抱歉电梯正在维修当中");
        }
        //获取符合条件的目标楼层
        List<Integer> floors = getfloorButtons(floorButtons, inFloor, sports);
        //根据用户电梯id 查询出大楼对应的楼层号 用来遍历电梯
        List<Floor> floorList = elevatorMapper.getFloorNumber(id);
        // 调用用户电梯运行方法
        inFloor = getRunElevator(floors, sports, inFloor, floorList);

        // 每次电梯停下之后,根据电梯id,把电梯楼层位置设置回电梯表中
        Integer buildingId = elevator.getBuildingId();
        elevatorMapper.updateInFloor(inFloor, sports, status, buildingId, id);

        //打印一下运行后的当前电梯数据
        return elevatorMapper.runElevator(id);
    }

    /**
     * 用户电梯往上运行
     *
     * @param floors    符合条件的目标楼层集合
     * @param sports    运行状态
     * @param inFloor   用户所在楼层电梯
     * @param floorList 用户所在楼层 所有楼层号
     */
    private Integer getRunElevator(List<Integer> floors, Integer sports, Integer inFloor, List<Floor> floorList) {
        if (CollectionUtils.isEmpty(floors)) {
            throw new RuntimeException("筛选出来的目标楼层为空");
        }
        //零时变量时间值
        Double j = 0.0;
        // 目标往上 :电梯运行状态  往上
        if (sports == 1) {
            log.info("电梯运行状态：{} 往上状态，", sports);
            //目标楼层正序排序
            Collections.sort(floors);
            floorList.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
            //获取目标楼层集合中最后一个参数值
            Integer max = floors.get(floors.size() - 1);
            for (Floor floor : floorList) {
                if (floor.getFloorNumber().equals(inFloor)) {
                    //遍历用户所在大楼的所有楼层 从用户所在位置往上开始正序遍历
                    for (int i = floor.getFloorNumber(); i <= max; i++) {
                        j++;
                        Double time = timeCount * j;
                        inFloor = i;
                        log.info("电梯往下运行至{}层,耗时{}秒,开门", inFloor, time);
                        if (floors.contains(i)) {
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒,开门", inFloor, time);
                            floors.remove(floors.indexOf(i));
                            log.info("关门继续运行");
                        }
                    }
                }
                break;
            }
            log.info("电梯运行完所有下行楼层");
        }
        //目标往下  电梯运行状态  往下
        else if (sports == 2) {
            log.info("电梯运行状态： {}往下状态，", sports);
            //降序排序
            Collections.reverse(floors);
            floorList.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
            //获取目标楼层集合中第一个参数值
            Integer min = floors.get(0);
            for (Floor floor : floorList) {
                if (floor.getFloorNumber().equals(inFloor)) {
                    //遍历用户所在大楼的所有楼层 从用户所在位置往下开始正序遍历
                    for (int i = floor.getFloorNumber(); i >= min; i--) {
                        j++;
                        Double time = timeCount * j;
                        inFloor = i;
                        log.info("电梯往下运行至{}层,耗时{}秒,开门", inFloor, time);
                        if (floors.contains(i)) {
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒,开门", inFloor, time);
                            floors.remove(floors.indexOf(i));
                            log.info("关门继续运行");
                        }
                    }
                    break;
                }
            }
            log.info("电梯运行完所有下行楼层");
        }
        return inFloor;
    }

    /**
     * 运行电梯::根据目标楼层 与 运行状态 筛选出 符合条件的目标楼层
     *
     * @param floorButtons 目标楼层按钮
     * @param inFloor      当前电梯所在楼层
     * @param sports       当前电梯运行的状态
     * @return 返回符合条件的目标楼层
     */
    private List<Integer> getfloorButtons(Set<Integer> floorButtons, Integer inFloor, Integer sports) {
        List<Integer> list = new ArrayList<>();
        for (Integer button : floorButtons) {
            if (!(button == 4 || button == 14 || button == 18)) {
                //如果电梯往上运行
                if (sports == 1) {
                    //只获取 >= 当前楼层的目标电梯楼层
                    if (button >= inFloor) {
                        list.add(button);
                    }
                }//如果电梯是往下运行就排除大于等与当前楼层的目标楼层
                if (sports == 2) {
                    if (button <= inFloor) {
                        list.add(button);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 呼叫电梯 电梯往下运行
     *
     * @param floorNumber 用户所在楼层号
     * @param inFloor     电梯所在楼层
     * @param floors      当前大楼所属的楼层对象
     * @param elevator    当前电梯对象
     */
    public void callElevatorDown(Integer floorNumber, Integer inFloor, List<Floor> floors, Elevator elevator) {
        //零时变量时间值
        Double j = 0.0;
        log.info("您在{}层", floorNumber);
        Integer sports = elevator.getSports();
        if (sports == 3) {
            sports = 2;
        }
        //目标往下::电梯往下运行中
        if (sports == 2) {
            //楼层号倒序排序
            floors.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
            Floor max = floors.get(0);
            Floor min = floors.get(floors.size() - 1);
            //遍历楼层对象
            for (Floor floor : floors) {
                if (floor.getFloorNumber().equals(inFloor)) {
                    //在比较电梯与楼层的位置 如果在同一层就开门,
                    if (floorNumber.equals(inFloor)) {
                        log.info("电梯在{}层,耗时{}秒,开门", inFloor, timeCount);
                        break;
                    }
                    //如果电梯楼层高于用户楼层,电梯从上往下来接用户
                    if (floor.getFloorNumber() > floorNumber) {
                        for (int i = floor.getFloorNumber(); i >= floorNumber; i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        log.info("电梯到达楼层,开门");
                        break;
                        //如果电梯楼层低于用户楼层,电梯运行到底层
                    } else if (floor.getFloorNumber() < floorNumber) {
                        for (int i = floor.getFloorNumber(); i >= min.getFloorNumber(); i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        //再从最底层运行到最高层, 正序排序
                        floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
                        min = floors.get(1);
                        max = floors.get(floors.size() - 1);
                        for (int i = min.getFloorNumber(); i <= max.getFloorNumber(); i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }
                        //电梯从上往下接到用户 倒序排序
                        floors.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
                        max = floors.get(1);
                        for (int i = max.getFloorNumber(); i >= floorNumber; i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        log.info("电梯到达楼层,开门");
                        break;
                    }
                }
            }
            //目标往下  电梯往上运行当中
        } else if (sports == 1) {
            //楼层号正序排序
            floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
            Floor max = floors.get(floors.size() - 1);
            Floor min = floors.get(0);
            for (Floor floor : floors) {
                if (floor.getFloorNumber().equals(inFloor)) {
                    //在比较电梯与楼层的位置 如果在同一层就开门,
                    if (floorNumber.equals(inFloor)) {
                        log.info("电梯在{}层,耗时{}秒,开门", inFloor, timeCount);
                        break;
                    }
                    if (floor.getFloorNumber() < floorNumber || floor.getFloorNumber() > floorNumber) {
                        //如果用户目标往下走: 电梯正往上行 就楼层运行到最顶层 掉头
                        for (int i = floor.getFloorNumber(); i <= max.getFloorNumber(); i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }
                        //倒序排序 电梯从上而下来接用户
                        floors.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
                        max = floors.get(1);
                        for (int i = max.getFloorNumber(); i >= floorNumber; i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        log.info("电梯到达楼层,开门");
                        break;
                    }
                }
            }
        }
        //把电梯状态更新回数据库
        Integer id = elevator.getId();
        sports = 2;
        Integer buildingId = elevator.getBuildingId();
        Integer status = elevator.getStatus();
        elevatorMapper.updateElevator(id, inFloor, sports, status, buildingId);
    }

    /**
     * 呼叫电梯::电梯往上运行
     *
     * @param floorNumber 用户所在楼层
     * @param inFloor     电梯所在楼层
     * @param floors      当前大楼所属的楼层对象
     * @param elevator    距离最近的电梯对象
     */
    public void callElevatorUP(Integer floorNumber, Integer inFloor, List<Floor> floors, Elevator elevator) {

        //零时变量时间值
        Double j = 0.0;
        //在比较电梯与楼层的位置 如果在同一层就开门,
        if (floorNumber.equals(inFloor)) {
            log.info("电梯在{}层,耗时{}秒,开门", inFloor, timeCount);
        }
        Integer sports = elevator.getSports();
        if (sports == 3) {
            sports = 1;
        }
        //电梯往上运行中 往上运行
        if (sports == 1) {
            //楼层号正序排序
            floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
            Floor max = floors.get(floors.size() - 1);
            Floor min = floors.get(0);
            //遍历楼层对象
            for (Floor floor : floors) {
                //如果楼层号与电梯所在位置相同 就获取 那个楼层号
                if (floor.getFloorNumber().equals(inFloor)) {
                    //在比较电梯与楼层的位置 如果在同一层就开门,
                    if (floorNumber.equals(inFloor)) {
                        log.info("电梯在{}层,耗时{}秒,开门", inFloor, timeCount);
                        break;
                    }
                    //电梯往上运行时:   如果电梯楼层,低于用户楼层
                    if (floor.getFloorNumber() < floorNumber) {
                        //遍历电梯楼层,电梯从下往上来接用户
                        for (Integer i = floor.getFloorNumber(); i <= floorNumber; i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }
                        log.info("电梯到达楼层,开门");
                        break;
                        //电梯往上运行时: 如果电梯楼层,高于用户楼层,
                    } else if (floor.getFloorNumber() > floorNumber) {
                        //电梯运行到顶楼
                        for (int i = floor.getFloorNumber(); i <= max.getFloorNumber(); i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        //  再往下运行到最低楼 倒序
                        floors.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
                        max = floors.get(1);
                        min = floors.get(floors.size() - 1);
                        for (int i = max.getFloorNumber(); i >= min.getFloorNumber(); i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }

                        //再从最低楼往上运行至用户楼层 正序
                        floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
                        min = floors.get(1);
                        for (int i = min.getFloorNumber(); i <= floorNumber; i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }
                        break;
                    }
                }
            }
            //电梯往下运行中
        } else if (sports == 2) {
            //楼层号倒序排序
            floors.sort((f1, f2) -> f2.getFloorNumber() - f1.getFloorNumber());
            Floor min = floors.get(floors.size() - 1);
            //遍历楼层对象
            for (Floor floor : floors) {
                //如果楼层号与电梯所在位置相同 就获取 那个楼层号
                if (floor.getFloorNumber().equals(inFloor)) {
                    //在比较电梯与楼层的位置 如果在同一层就开门,
                    if (floorNumber.equals(inFloor)) {
                        log.info("电梯在{}层,耗时{}秒,开门", inFloor, timeCount);
                        break;
                    }
                    //如果电梯楼层低于用户楼层 或者高于用户楼层   运行到最底层
                    if (floor.getFloorNumber() < floorNumber || floor.getFloorNumber() > floorNumber) {
                        for (int i = floor.getFloorNumber(); i >= min.getFloorNumber(); i--) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往下运行至{}层,耗时{}秒", inFloor, time);
                        }
                        //往上运行时 正序排除
                        floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
                        min = floors.get(1);
                        //在从最底层往上来接用户
                        for (int i = min.getFloorNumber(); i <= floorNumber; i++) {
                            j++;
                            Double time = timeCount * j;
                            inFloor = i;
                            log.info("电梯往上运行至{}层,耗时{}秒", inFloor, time);
                        }
                        break;
                    }
                }
            }
        }
        //把电梯状态更新回数据库
        Integer id = elevator.getId();
        sports = 1;
        Integer buildingId = elevator.getBuildingId();
        Integer status = elevator.getStatus();
        elevatorMapper.updateElevator(id, inFloor, sports, status, buildingId);

    }

    /**
     * @param floors      楼层对象集合
     * @param floorNumber 用户所在楼层
     * @return 返回楼层对象
     */
    public static Floor getFloorObject(List<Floor> floors, Integer floorNumber) {
        //正序排序楼层号
        floors.sort((f1, f2) -> f1.getFloorNumber() - f2.getFloorNumber());
        //获取最大楼层号
        Floor max = floors.get(floors.size() - 1);
        if (floorNumber > max.getFloorNumber()) {
            throw new RuntimeException("您输入的楼层号不正确");
        }
        Floor fl = null;
        //1.获取当前楼层号对应的楼层对象
        for (Floor floor1 : floors) {
            if (floor1.getFloorNumber().equals(floorNumber)) {
                fl = floor1;
                break;
            }
        }
        return fl;
    }

    /**
     * 获取用户楼层与 电梯楼层最近距离的电梯楼层
     *
     * @param elevatorList 电梯对象集合
     * @param floorNumber  用户所在楼层
     * @param buttons      用户按钮 : 上 下
     * @return 返回电梯楼层
     */
    public static Elevator getElevatorObject(List<Elevator> elevatorList, Integer floorNumber, String buttons) {
        //最短距离的变量
        int min = 0;
        //电梯对象的零时变量
        Elevator ele = null;
        //最短距离楼层值
        int distance = 0;
        if (CollectionUtils.isEmpty(elevatorList)) {
            throw new RuntimeException("电梯对象集合不能为空");
        }
        //遍历获取到电梯对象
        for (Elevator elevator : elevatorList) {
            if (elevator.getStatus() != 1) {
                throw new RuntimeException("电梯不可用");
            }
            Integer sports = elevator.getSports();
            //根据用户点击的按钮优先选择相同方向的电梯
            if ("上".equals(buttons)) {
                if (sports == 1) {
                    if (elevator.getInFloor().equals(floorNumber)) {
                        distance = -1;
                    }
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() < floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                    //如果没有相同方向的就只能选择正在运行的
                } else if (sports == 2) {
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() <= floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                    //如果没有正在运行的,就只能等待禁止状态的电梯
                } else if (sports == 3) {
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() <= floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                }
                //往下运行
            } else if ("下".equals(buttons)) {
                if (sports == 2) {
                    if (elevator.getInFloor().equals(floorNumber)) {
                        distance = -1;
                    }
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() < floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                    //如果没有相同方向的就只能选择正在运行的
                } else if (sports == 1) {
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() <= floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                    //如果没有正在运行的,就只能等待禁止状态的电梯
                } else if (sports == 3) {
                    //排除大于用户楼层的电梯对象
                    if (elevator.getInFloor() <= floorNumber) {
                        distance = Math.abs(floorNumber - elevator.getInFloor());
                    }
                }
            }
            if (distance == 0) {
                //如果只有排除掉的电梯,那就以排除的电梯进行比较
                distance = Math.abs(floorNumber - elevator.getInFloor());
            }
            if (min == 0 || distance < min) {
                //把最短距离绝对值赋值给 最小变量
                min = distance;
                //在把最短距离零时变量清空
                distance = 0;
                //把电梯对象返回
                ele = elevator;
            }
        }
        return ele;
    }
}
