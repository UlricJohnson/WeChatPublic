package com.casaba.service.impl;

import com.casaba.entity.Data;
import com.casaba.mapper.DataMapper;
import com.casaba.service.IDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * created by casaba-u on 2018/8/27
 */
@Service
public class DataService implements IDataService {

    private static final Log LOGGER = LogFactory.getLog(DataService.class);

    @Resource
    private DataMapper dataMapper;

    /**
     * 获取全部数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    @Override
    public List<Data> findAllData() {
        List<Data> dataList = dataMapper.selectAll();

        return dataList;
    }

    /**
     * 添加一项数据
     *
     * @param data
     * @author casaba-u
     * @date 2018/8/27
     */
    @Override
    public boolean addOne(Data data) {
        LOGGER.info("=====接收到的数据：" + data);

        boolean insertSuccess = dataMapper.insert(data);

        return insertSuccess;
    }

    /**
     * 根据 regCode 查询数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    @Override
    public Data queryByRegCode(String regCode) {
        LOGGER.info("=====接收到的数据：" + regCode);

        Data data = dataMapper.selectByRegCode(regCode);

        LOGGER.info("=====返回数据：" + data);

        return data;
    }
}
