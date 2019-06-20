package cn.lizi.lizi.service.login;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.dingshi.Test01Model;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.model.putonglei.user;
import cn.lizi.lizi.service.other.BaseService;

public interface LoginService extends BaseService {


    String test01(Test01Model model);
    String test02(Integer i);
    String rule(user model) throws Exception;

    /**
     * 获取用户信息
     * @param model
     * @return
     */
    ResultModel getUserInfo(UserModel model);
}
