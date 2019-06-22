package cn.lizi.lizi.controller;

import cn.lizi.lizi.ZiDingYiZhuJie.authority;
import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class loginController{
    @Autowired
    private LoginService loginService;

    /**
     * 获取验证码
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendVerificationCode")
    ResultModel sendVerificationCode(UserModel model){
        return loginService.sendVerificationCode(model);
    }

    /**
     * 校验token是否过期
     * @param model
     * @return
     */
    @authority
    @ResponseBody
    @RequestMapping("/checkToken")
    ResultModel chackToken(UserModel model){
        return ResultModel.getSuccess("成功",null);
    }


    /**
     * 微信回调 获取用户信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/UserInfo")
    public String userInfo(Object model){

        return "null";
    }

    /**
     * 用户注册
     * @param model
     * @return
     */
    //@authority
    @ResponseBody
    @RequestMapping("/register")
    public ResultModel register(UserModel model) {
        return loginService.register(model);
    }

    /**
     * 用户登陆
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ResultModel login(UserModel model) {
        return loginService.login(model);
    }

    /**
     * 获取用户信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public ResultModel getUserInfo(UserModel model){
        return loginService.getUserInfo(model);
    }



}
