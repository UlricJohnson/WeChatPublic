package com.casaba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

/**
 * created by casaba-u on 2018/9/19
 */
@RequestMapping("/redirect")
@Controller
public class RedirectController {

    /**
     * 重定向到调查填写页面
     *
     * @author casaba-u
     * @date 2018/9/19
     */
    @RequestMapping("/toQuery")
    public ModelAndView toQuery() {
        String target = "https://www.wjx.cn/m/27872200.aspx";

        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:" + target);

        return mv;
    }

    /**
     * 重定向到公众号的历史消息
     *
     * @author casaba-u
     * @date 2018/9/19
     */
    @RequestMapping("/toHistory")
    public ModelAndView toHistory() {
        String target = "https://mp.weixin.qq.com/mp/profile_ext?action=home__biz=MzU5MTY2NTI0OA==scene=124#wechat_redirect";

        ModelAndView mv = new ModelAndView();

        mv.setViewName("redirect:" + target);

        return mv;
    }

}
