package com.casaba.service;

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
    boolean saveComplaintSheet(String certificate, String username, String contactNum, String sketch, String details,String imgUrl) throws Exception;

}
