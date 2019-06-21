package cn.lizi.lizi.service.login;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.other.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService extends BaseService {

    /**
     * 获取注册
     * @param model
     * @return
     */
    ResultModel register(UserModel model);

    /**
     * 用户登陆
     * @param model
     * @return
     */
    ResultModel login(UserModel model);

    /**
     * 获取用户信息
     * @param model
     * @return
     */
    ResultModel getUserInfo(UserModel model);


    /**
     * 获取验证码
     * @param model
     * @return
     */
    ResultModel sendVerificationCode(UserModel model);



}
