package org.lufengxue.core.service;


import com.github.pagehelper.PageInfo;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:38
 * 描    述:
 */
public interface PagingService<T> {

    /**
     * 查询所有并分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<T> findByPage(Integer pageNo, Integer pageSize);


    /**
     * 根据查询条件 record 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param record
     * @return
     */
    PageInfo<T> findByPage(Integer pageNo, Integer pageSize, T record);


    /**
     * 根据查询条件exmaple来分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param example
     * @return
     */
    PageInfo<T> findByPageExample(Integer pageNo, Integer pageSize, Object example);


}
