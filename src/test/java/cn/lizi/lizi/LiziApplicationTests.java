package cn.lizi.lizi;

import cn.lizi.lizi.service.AI.xingtai123Service;
import cn.lizi.lizi.utils.RedisUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiziApplicationTests {
    @Autowired
    xingtai123Service i123Service;


    @Test
    public void saveJiazhuang(){
        i123Service.saveType03();
    }

}
