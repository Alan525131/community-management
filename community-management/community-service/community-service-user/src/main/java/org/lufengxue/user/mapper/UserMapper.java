package org.lufengxue.user.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lufengxue.pojo.user.dto.UserDto;
import org.lufengxue.pojo.user.po.UserPo;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.user.mapper
 * 日    期:  2022-03-2022/3/30
 * 时    间:  1:11
 * 描    述:
 */
@Mapper
public interface UserMapper {

    UserDto findByName(String username);

    Integer insert(UserPo userPo);

    Integer deleteId(String username);

    List<UserDto> findAll();

    Integer updateUser(UserPo userPo);
}
