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

    //门市  装修
    @Scheduled(cron = "0 59 23 * * ?")
    public void start01(){
        System.out.print("开始执行爬虫程序\n");
        xingtai123Service.saveXingTai123Data();
    }
    //招聘
    @Scheduled(cron = "0 58 23 * * ?")
    public void start02(){
        System.out.print("开始执行爬虫程序\n");
        xingtai123Service.saveZP();
    }
    //卡票卷
    @Scheduled(cron = "0 57 23 * * ?")
    public void start03(){
        System.out.print("开始执行爬虫程序\n");
        xingtai123Service.savekapiaojuan();
    }

    //白色那个
    @Scheduled(cron = "0 55 23 * * ?")
    public void saveType03(){
        System.out.print("开始执行爬虫程序\n");
        xingtai123Service.saveType03();
    }


}