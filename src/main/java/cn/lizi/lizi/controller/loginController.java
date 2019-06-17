package cn.lizi.lizi.controller;

import cn.lizi.lizi.model.dingshi.Test01Model;
import cn.lizi.lizi.model.putonglei.user;
import cn.lizi.lizi.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class loginController{
    @Autowired
    private LoginService loginService;

    //@authority
    @ResponseBody
    @RequestMapping("/login")
    public String login(user model) {
        return loginService.login(model);
    }

    @ResponseBody
    @RequestMapping("/test01")
    public String test01(Test01Model model) {
        return loginService.test01(model);
    }

    @ResponseBody
    @RequestMapping("/rule")
    public String test02(user model) throws Exception {
        return loginService.rule(model);
    }

    //@ResponseBody
    @RequestMapping("/111")
    public String test001(){
        //return new ModelAndView("index.html");
        return "ServiceImpl.ftl";
    }

    /**
     * 微信回调 获取用户信息
     * @param model
     * @return
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo(Object model){

        return "null";
    }


}
