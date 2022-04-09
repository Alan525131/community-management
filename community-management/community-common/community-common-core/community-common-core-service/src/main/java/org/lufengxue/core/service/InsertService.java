package org.lufengxue.core.service;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/31
 * 时    间:  13:38
 * 描    述:
 */
public interface InsertService<T> {
    /**
     * 添加记录
     * @param record
     * @return
     */
    int insert(T record);

}