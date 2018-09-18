package com.casaba.controller;

import com.casaba.entity.Data;
import com.casaba.service.IDataService;
import com.casaba.service.impl.DataService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xalan.internal.xsltc.dom.DOMAdapter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * created by casaba-u on 2018/8/27
 */

@RequestMapping("/data")
@RestController
public class DataController {

    private static final Log LOGGER = LogFactory.getLog(DataController.class);

    @Resource
    private IDataService dataService;

    /**
     * get方式获取全部数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Map<String, Object> getAllData(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<Data> dataList = dataService.findAllData();

        Map<String, Object> resultMap = new HashMap<>();

        if (dataList.isEmpty()) { // 没有数据
            resultMap.put("success", false);
            resultMap.put("msg", "没有数据");
        } else {
            resultMap.put("success", true);
            resultMap.put("result", dataList);
        }

        LOGGER.info("=====返回的数据：" + resultMap);

        return resultMap;
    }

    /**
     * 添加一项数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    @RequestMapping(value = "/addOne", method = RequestMethod.POST)
    public Map addOne(@RequestBody String jsonStr, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        LOGGER.info("=====接收到的数据：" + jsonStr);

        Map<String, Object> resultMap = new HashMap<>();

        JsonParser jsonParser = new JsonParser();

        JsonObject jsonObj = jsonParser.parse(jsonStr).getAsJsonObject();

        JsonObject dataJson = null;

        if (jsonObj.has("data")) {
            dataJson = jsonObj.get("data").getAsJsonObject();
        }

        String eventCode = dataJson.get("eventCode").getAsString();
        String eventData = dataJson.get("eventData").getAsString();
        String eventStatus = dataJson.get("eventStatus").getAsString();
        String eventTime = dataJson.get("eventTime").getAsString();
        String msgSeq = dataJson.get("msgSeq").getAsString();
        String regCode = dataJson.get("regCode").getAsString();
        String tdSerial = dataJson.get("tdSerial").getAsString();

        Data data = new Data();
        data.setEventCode(eventCode);
        data.setEventData(eventData);
        data.setEventStatus(eventStatus);
        data.setEventTime(eventTime);
        data.setMsgSeq(msgSeq);
        data.setRegCode(regCode);
        data.setTdSerial(tdSerial);

        LOGGER.info("=====保存：" + data);

        boolean addSuccess = false;
        try {
            addSuccess = dataService.addOne(data);
            resultMap.put("success", addSuccess);
            if (!addSuccess) { // 保存失败
                resultMap.put("msg", "未知原因");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg", e.getMessage());
        } finally {
            return resultMap;
        }
    }

    /**
     * 根据 regCode 查询数据
     *
     * @author casaba-u
     * @date 2018/8/27
     */
    @RequestMapping("/queryByRegCode")
    public Map queryByRegCode(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        Map<String, Object> resultMap = new HashMap<>();

        String regCode = request.getParameter("regCode");

        LOGGER.info("=====get参数：\n\t#regCode: " + regCode);

//        Data data = dataService.queryByRegCode(regCode);
        List<Data> dataList = dataService.queryByRegCode(regCode);

        if (null == dataList || dataList.size() <= 0) {
            resultMap.put("success", false);
            resultMap.put("msg", "没有数据");
        } else {
            resultMap.put("success", true);
            resultMap.put("result", dataList);
        }
        return resultMap;
    }

}
