package com.casaba.service;

import com.casaba.entity.Complaint;
import com.casaba.entity.User;

import java.util.List;

/**
 * created by Ulric on 2018/7/24
 */

public interface IComplaintService {

    /**
     * 把投诉单的内容存入数据库
     *
     * @author Ulric
     * @date 2018/7/24
     */
    boolean saveComplaintSheet(String certificate, String username, String contactNum, String sketch, String details, String imgUrl) throws Exception;

    /**
     * 根据用户(名)查找投诉单
     *
     * @author casaba-u
     * @date 2018/8/3
     */
    List<Complaint> findComplaintsByUser(User user);
}
