package com.casaba.service;

import com.casaba.entity.Data;

import java.util.List;

/**
 * created by casaba-u on 2018/8/27
 */

public interface IDataService {
    /**
     * 获取全部数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    List<Data> findAllData();

    /**
     * 添加一项数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    boolean addOne(Data data);

    /**
     * 根据 regCode 查询数据
     * @author casaba-u
     * @date 2018/8/27
     */
//    Data queryByRegCode(String regCode);
    List<Data> queryByRegCode(String regCode);
}
