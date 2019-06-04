package cn.lizi.lizi.service;

import cn.lizi.lizi.model.dingshi.Test01Model;
import cn.lizi.lizi.model.putonglei.user;

public interface LoginService extends BaseService  {


    String test01(Test01Model model);
    String test02(Integer i);
    String rule(user model) throws Exception;
}
