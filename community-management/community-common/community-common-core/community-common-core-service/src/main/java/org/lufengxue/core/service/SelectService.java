package org.lufengxue.core.service;

import java.util.List;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:38
 * 描    述:
 */
public interface SelectService<T> {

    /**
     * 查询所有
     *
     * @return
     */
    public List<T> selectAll();

    /**
     * 查询一个对象
     *
     * @param id
     * @return
     */
    public T selectByPrimaryKey(Object id);

    /**
     * 根据条件查询
     *
     * @param record
     * @return
     */
    public List<T> select(T record);


}
