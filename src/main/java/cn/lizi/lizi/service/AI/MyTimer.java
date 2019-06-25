package cn.lizi.lizi.service.AI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@EnableScheduling  //可以写在启动类上面
@Component
public class MyTimer {
    @Autowired
    xingtai123Service xingtai123Service;
    /**
     * 定时器01
     */
    @Scheduled(cron = "0 5 0 * * ?")
    public void start01(){
        System.out.print("一分钟时间到\n");
        xingtai123Service.saveXingTai123Data();
    }

}