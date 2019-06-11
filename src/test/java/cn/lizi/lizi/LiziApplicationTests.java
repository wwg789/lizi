package cn.lizi.lizi;

import cn.lizi.lizi.model.dingshi.Test01Model;
import cn.lizi.lizi.model.putonglei.user;
import cn.lizi.lizi.service.impl.LoginServiceImpl;
import cn.lizi.lizi.utils.RedisUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.security.auth.login.LoginContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiziApplicationTests {

	@Autowired
	RedisUtil redisUtil;

	@Test
	public void contextLoads() {
		ArrayList<Object> list = new ArrayList<>();
		HashMap<Object, Object> map = new HashMap<>();
		map.put("map01key01","map01value01");
		map.put("map01key02","map01value02");
		HashMap<Object, Object> map1 = new HashMap<>();
		map1.put("map02key01","map02value01");
		map1.put("map02key02","map02value02");
		HashMap<Object, Object> map2 = new HashMap<>();
		map2.put("map03key01","map03value01");
		map2.put("map03key02","map03value02");
		list.add(map);
		list.add(map1);
		list.add(map2);


}

	@Test
	public void test01() {
		//boolean lizi = redisUtil.set("lizi", "999999");
		String list = (String)redisUtil.get("list");



	}


	@Test
	public void test02() {
		System.out.print(redisUtil.expire("lizi",1));
	}
	@Test
	public void test03(){
		System.out.print(redisUtil.getExpire("lizi"));
	}
	@Test
	public void test04(){
		redisUtil.del("list");
	}
	@Test
	public void test05(){
		ArrayList<Object> list = new ArrayList<>();
		HashMap<Object, Object> map = new HashMap<>();
		map.put("map01key01","map01value01");
		map.put("map01key02","map01value02");
		HashMap<Object, Object> map1 = new HashMap<>();
		map1.put("map02key01","map02value01");
		map1.put("map02key02","map02value02");
		HashMap<Object, Object> map2 = new HashMap<>();
		map2.put("map03key01","map03value01");
		map2.put("map03key02","map03value02");
		list.add(map);
		list.add(map1);
		list.add(map2);



	}

	@Test
	public void test06(){
		System.out.print(redisUtil.lGet("list",0,-1));
	}



}
