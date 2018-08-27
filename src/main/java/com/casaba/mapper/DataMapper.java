package com.casaba.mapper;

import com.casaba.entity.Data;

import java.util.List;

/**
 * created by casaba-u on 2018/8/27
 */

public interface DataMapper {
    /**
     * 获取全部数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    List selectAll();

    /**
     * 根据 regCode 查询数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    Data selectByRegCode(String regCode);

    /**
     * 添加一项数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    boolean insert(Data data);

    /**
     * 批量添加
     *
     * @author casaba-u
     * @date 2018/8/27
     */
//    boolean insertBatch(List<Data> dataList);

}
