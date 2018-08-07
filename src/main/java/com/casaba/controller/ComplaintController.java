package com.casaba.controller;

import com.casaba.entity.Elevator;
import com.casaba.service.IComplaintService;
import com.casaba.service.IElevatorService;
import com.casaba.util.CommonUtil;
import com.casaba.util.WeChatSignUtil;
import com.casaba.util.WeChatUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * created by Ulric on 2018/7/22
 */

@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    private static final Log LOGGER = LogFactory.getLog(ComplaintController.class);

    @Resource
    private IElevatorService iElevatorService;

    @Resource
    private IComplaintService iComplaintService;


    /**
     * 从投诉电梯信息填写页跳转到投诉页面
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping("/toComplaint_fillIn")
    public ModelAndView toComplaint_fillIn(String certificateOfUse) {
        LOGGER.info("=====您要投诉的电梯为：" + certificateOfUse);

        ModelAndView mv = new ModelAndView();


        // 查找出该电梯的设备地址
        Elevator elevator = iElevatorService.findByCertificate(certificateOfUse);

        if(elevator==null){
            mv.setViewName("not_found");
            mv.addObject("msg","您查找的数据不存在");
        }else{
            mv.setViewName("complaint");
            mv.addObject("elevator", elevator);
        }

        return mv;
    }

    /**
     * 从电梯信息列表页跳到投诉页面
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping(value = "/toComplaint_eleInfo", method = RequestMethod.POST)
//    @ResponseBody   // @ResponseBody 注解表示返回的字符串不是视图名称，而是JSON字符串
    public ModelAndView toComplaint_eleInfo(String certificateOfUse, String deviceAddress) {
        Elevator elevator = new Elevator();
        elevator.setCertificateOfUse(certificateOfUse);
        elevator.setDeviceAddress(deviceAddress);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("complaint");
        mv.addObject("elevator", elevator);

        return mv;
    }

    /**
     * 执行投诉操作
     *
     * @author Ulric
     * @date 2018/7/23
     */
    @RequestMapping("/doComplaint")
    public ModelAndView doComplaint(String certificate, String sketch, String username, String contactNum, String
            details, String imgUrl) {
        LOGGER.info("=====接收到的参数：\n\t#certificate：" + certificate +
                "\n\tsketch：" + sketch +
                "\n\tusername：" + username +
                "\n\tcontactNum：" + contactNum +
                "\n\tdetails：" + details +
                "\n\t#imgUrl：" + imgUrl);

        ModelAndView mv = new ModelAndView();
        mv.addObject("username", username);
        mv.addObject("certificate", certificate);

        // 将用户（投诉人）、投诉单、电梯信息保存到数据库
        try {
            boolean isSuccess =
                    iComplaintService.saveComplaintSheet(certificate, username, contactNum, sketch, details, imgUrl);
            if (!isSuccess) {
                throw new RuntimeException("保存数据失败");
            }
            mv.setViewName("complaint_success");
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("complaint_fail");
        } finally {
            return mv;
        }
    }

    /**
     * 上传图片
     *
     * @author Ulric
     * @date 2018/7/30
     */
    @RequestMapping("/saveImage")
    @ResponseBody
    public Map uploadImage(HttpServletRequest request, String mediaId) {
        LOGGER.info("=====接收到的参数：\n\t#mediaId：" + mediaId);

        Map<String, Object> resultMap = new HashMap<>();

        String imgName = "";
        // 根据 mediaId 获取图片的字节输入流
        InputStream inStrm = getMedia(mediaId);
        byte[] bytes = new byte[1024];
        int len = 0;
        FileOutputStream fileOutStrm = null;

        // 服务器保存图片的路径
        String webRootPath = request.getSession().getServletContext().getRealPath("/");
        if (webRootPath.endsWith("/") || webRootPath.endsWith("\\")) {
            String temp = webRootPath.substring(0, webRootPath.length() - 1);
            webRootPath = temp;
        }
        String imgRootPath = webRootPath + "/img_upload";
        File imgDir = new File(imgRootPath);
        if(!imgDir.exists()){
            imgDir.mkdirs();
        }
        imgName = System.currentTimeMillis() + CommonUtil.genRandom(4) + ".png";
        try {
            File imgFile = new File(imgRootPath + "/" + imgName);
            if (!imgFile.exists()) {
                imgFile.createNewFile();
            }
            fileOutStrm = new FileOutputStream(imgRootPath + "/" + imgName);
            while ((len = inStrm.read(bytes)) != -1) {
                fileOutStrm.write(bytes, 0, len);
            }
            resultMap.put("success", true);
            resultMap.put("imgUrl", imgRootPath + "/" + imgName);
        } catch (IOException e) {
            resultMap.put("success", false);
            resultMap.put("msg", e.getMessage());
            e.printStackTrace();
        } finally {
            if (inStrm != null) {
                try {
                    inStrm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutStrm != null) {
                try {
                    fileOutStrm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return resultMap;
        }


//        String imageBase64Str = getImageBase64Str(localData);
//
//        // 对字节数组字符串进行 base64 解码，并生成图片
//        if (null == imageBase64Str) { return null; }
//
//        BASE64Decoder base64Decoder = new BASE64Decoder();
//
//        try {
//            byte[] bytes = base64Decoder.decodeBuffer(imageBase64Str);
//            for (int i = 0; i < bytes.length; i++) {
//                if (bytes[i] < 0) { // 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//
//            // 生成图片
//            String webRootPath = request.getSession().getServletContext().getRealPath("/"); // 项目根目录
//            LOGGER.info("=====项目根目录：" + webRootPath);
//            if (webRootPath.endsWith("/") || webRootPath.endsWith("\\")) {
//                // 如果最后有 “/” 或者 “\”，则去掉
//                String temp = webRootPath.substring(0, webRootPath.length() - 1);
//                webRootPath = temp;
//            }
//            String imgDirPath = webRootPath + "/img_upload";
//            String imgFilePath = imgDirPath + "/" + System.currentTimeMillis() + ".jpg";
//
//            OutputStream outStrm = new FileOutputStream(imgFilePath);
//            outStrm.write(bytes);
//            outStrm.flush();
//            outStrm.close();
//
//            resultMap.put("success", true);
//        } catch (IOException e) {
//            e.printStackTrace();
//            resultMap.put("success", false);
//        } finally {
//            return resultMap;
//        }
    }

    /**================================================**/

    /**
     * 根据 mediaId 获取临时素材的输入字节流
     *
     * @author Ulric
     * @date 2018/7/31
     */
    private InputStream getMedia(String mediaId) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get";
        String access_token = WeChatUtil.getAccessToken();
        String params = "access_token=" + access_token + "&media_id=" + mediaId;
        InputStream inStrm = null;
        try {
            String urlNameString = url + "?" + params;
            URL urlGet = new URL(urlNameString);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            // 获取文件转化为byte流
            inStrm = http.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inStrm;
    }

    /**
     * 将图片转化成 base64 字符串
     *
     * @param imgPath 图片的路径（可以是网络路径）
     * @author Ulric
     * @date 2018/7/30
     */
//    private String getImageBase64Str(String imgPath) {
//        LOGGER.info("=====接收到的参数：\n\t#imgPath：" + imgPath);
//
//        InputStream inStrm = null;
//        byte[] bytes = null;
//
//        // 读取图片字节数组
//        try {
//            inStrm = new FileInputStream(imgPath);
//            // available()：获取从此输入流中可以读取（或跳过）的剩余字节数的估计值
//            bytes = new byte[inStrm.available()];
//            inStrm.read(bytes); // read()：从该输入流读取最多 bytes.length 个字节的数据为字节数组。
//            inStrm.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 对字节数组进行 base64 编码（会生成编码后的字节字符串）并返回
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        return base64Encoder.encode(bytes);
//    }

    /**
     * 将 base64 字符串转化成图片
     *
     * @author Ulric
     * @date 2018/7/30
     */
//    private boolean genImage(String base64String) {
//        // 对字节数组字符串进行 base64 解码，并生成图片
//        if (null == base64String) { return false; }
//
//        BASE64Decoder base64Decoder = new BASE64Decoder();
//
//        try {
//            byte[] bytes = base64Decoder.decodeBuffer(base64String);
//            for (int i = 0; i < bytes.length; i++) {
//                if (bytes[i] < 0) { // 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
}
