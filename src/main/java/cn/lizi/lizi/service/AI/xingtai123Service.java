package cn.lizi.lizi.service.AI;

import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.mapper.LoginMapper;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.utils.DateUtils4Java8;
import cn.lizi.lizi.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Component
@Slf4j
public class xingtai123Service {

    @Autowired
    ForumMapper forumMapper;

    @Autowired
    LoginMapper loginMapper;

    /**
     * Elements taga = document.getElementsByTag("tr");  根据标签获取一组属性
     * element.attr("href")  获取标签的属性值
     */
    public void saveXingTai123Data() {

        String path = "http://www.xingtai123.com/menshi/index.asp?page=";

        //获取昨天
        ArrayList<ForumInfoModel> forumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pathUrl = path+i;
            if(getParentUtl(pathUrl,forumList) == false){
                break;
            }
        }
        log.info("门市-爬虫结果保存 time {} ，条数 {}",DateUtils4Java8.getCurDateTimeFull(),forumList.size());
        //保存数据
        forumMapper.addForumList(forumList);
    }

    //获取门市信息内容
    public boolean getParentUtl(String path,ArrayList<ForumInfoModel> forumList) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Document document = httpClientUtil.getDocument(path,new HashMap<>(),new HashMap<>());
        if(null != document){
            Elements bk_blue = document.getElementsByClass("bk_blue");
            String yesterday = DateUtils4Java8.beforeNDaysDate("MM.dd",1);
            //String yesterday = DateUtils4Java8.getCurDateTime("MM.dd");
            if(null != bk_blue){
                //获取用户信息
                UserModel userInfo = loginMapper.getUserInfo(UserModel.builder()
                        .userId(4).build());
                for (int i = 0; i < bk_blue.size() ; i++) {
                    Element element = bk_blue.get(i);
                    if(null != element){
                        ForumInfoModel forumInfoModel = new ForumInfoModel();
                        //获取信息
                        Elements zi12_black = element.getElementsByClass("zi12_black");
                        if(null != zi12_black){
                            Element element1 = zi12_black.get(0);
                            if(!yesterday.equals(element1.ownText())){
                                return false;
                            }
                            String content = "";
                            Elements b = element.getElementsByTag("b");
                            if(null != b){
                                content = b.get(0).ownText();
                            }
                            Elements span = element.getElementsByClass("zi14_black_xi");
                            if(null != b){
                                content += span.get(0).ownText();
                            }
                            forumInfoModel.setForumContent(content);
                            //添加用户信息
                            forumInfoModel.setUserId(userInfo.getUserId());
                            forumInfoModel.setNickName(userInfo.getNickName());
                            forumInfoModel.setForumParentId(34);
                            forumInfoModel.setUserHeadPortraitUrl(userInfo.getHeadPortraitUrl());
                            forumInfoModel.setForumSubject("系统自动搜所结果 门市出租，房屋出租信息");
                            forumInfoModel.setCreateTime(new Date());
                            forumList.add(forumInfoModel);
                        }

                    }
                }
            }
        }
        return true;
    }


}
