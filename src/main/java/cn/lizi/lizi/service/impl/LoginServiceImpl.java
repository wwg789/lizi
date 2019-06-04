package cn.lizi.lizi.service.impl;

import cn.lizi.lizi.ZiDingYiZhuJie.authority;
import cn.lizi.lizi.model.dingshi.Test01Model;
import cn.lizi.lizi.model.putonglei.user;
import cn.lizi.lizi.service.LoginService;
import cn.lizi.lizi.utils.KieSessionUtils;
import cn.lizi.lizi.utils.KieSessionUtils1;
import org.kie.api.runtime.KieSession;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public String login(user model) {
        System.out.println(model.getUserName());
        this.test01(Test01Model.builder().param01(10).build());
        return model.getUserName();
    }

    @Async("asyncServiceExecutor")
    @Override
    public String test01(Test01Model model) {
        if(model == null || model.getParam01() == null)return "参数不完整";
        for (int i = 0; i < model.getParam01(); i++) {
            System.out.println("线程==" +Thread.currentThread().getName()+ " |等待10s  参数"+i);
            test02(i);
        }
        return "执行完成 返回";
    }


    @Override
    public String test02(Integer i) {
        try
        {
            Thread.currentThread().sleep(50000);//毫秒
        }
        catch(Exception e){}
        return "testo2完成";
    }


    @Override
    public String rule(user model) throws Exception {
        KieSession allRules = KieSessionUtils1.getAllRules();
        allRules.insert(new Double("1.11"));
        //激活某一组 带组名的规则需要激活
        allRules.getAgenda().getAgendaGroup("jihuo - a").setFocus();

        allRules.fireAllRules();
        allRules.dispose();
        return "123";
    }

}
