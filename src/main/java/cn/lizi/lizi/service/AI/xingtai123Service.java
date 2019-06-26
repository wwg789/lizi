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


    public void test() {

        String path = "http://www.xingtai123.com/youjiao/index.asp?page=";
        //获取昨天
        ArrayList<ForumInfoModel> forumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pathUrl = path+i;
            if(getParentUtl(pathUrl,34,forumList) == false){
                break;
            }
        }
        log.info("测试结果 time {} ，条数 {}",DateUtils4Java8.getCurDateTimeFull(),forumList.size());
    }
    /**
     * 卡票卷
     */
    public void savekapiaojuan() {

        String path = "http://www.xingtai123.com/kapiaoquan/index.asp?page=";
        //获取昨天
        ArrayList<ForumInfoModel> forumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pathUrl = path+i;
            if(getParentUtl03(pathUrl,23,forumList) == false){
                break;
            }
        }
        log.info("卡票卷 - 爬虫结果 time {} ，条数 {}",DateUtils4Java8.getCurDateTimeFull(),forumList.size());
        forumMapper.addForumList(forumList);
    }
    /**
     * 招聘信息
     */
    public void saveZP() {

        String path = "http://www.xingtai123.com/youjiao/index.asp?page=";
        //获取昨天
        ArrayList<ForumInfoModel> forumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pathUrl = path+i;
            if(getParentUtl03(pathUrl,34,forumList) == false){
                break;
            }
        }
        log.info("招聘 - 爬虫结果 time {} ，条数 {}",DateUtils4Java8.getCurDateTimeFull(),forumList.size());
        forumMapper.addForumList(forumList);
    }


    /**
     * Elements taga = document.getElementsByTag("tr");  根据标签获取一组属性
     * element.attr("href")  获取标签的属性值
     *   门市信息 装修信息
     */
    public void saveXingTai123Data() {

        HashMap<String, Integer> parents = new HashMap<>();
        parents.put("menshi",34);
        parents.put("jiazhuang",33);
        parents.put("zuche",31);
        parents.put("yinshua",10);
        parents.put("kuaiji",8);
        parents.put("lvshi",7);
        parents.put("youjiao",4);



        parents.forEach((parentPath,parentId)->{
            //获取内容
            ArrayList<ForumInfoModel> forumList = getContent(parentPath,parentId);
            //保存内容
            if(forumList!=null && forumList.size()>0){
                forumMapper.addForumList(forumList);
                log.info(parentPath+"-爬虫结果保存 time {} ，条数 {}",DateUtils4Java8.getCurDateTimeFull(),forumList.size());
            }

        });
    }

    //获取内容
    private ArrayList<ForumInfoModel> getContent(String parentPath,Integer parentId) {
        String path = "http://www.xingtai123.com";
        path += "/"+parentPath+"/index.asp?page=";
        ArrayList<ForumInfoModel> forumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pathUrl = path+i;
            if(getParentUtl(pathUrl,parentId,forumList) == false){
                break;
            }
        }
        return forumList;
    }

    //获取信息内容
    public boolean getParentUtl(String path,Integer parentId,ArrayList<ForumInfoModel> forumList) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Document document = httpClientUtil.getDocument(path,new HashMap<>(),new HashMap<>());
        if(null != document){
            Elements bk_blue = document.getElementsByClass("bk_blue");
            String todat = DateUtils4Java8.getCurDateTime("MM.dd");
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
                        if(null != zi12_black && zi12_black.size()>0){
                            Element element1 = zi12_black.get(0);
                            if(!todat.equals(element1.ownText())){
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
                            forumInfoModel.setForumParentId(parentId);
                            forumInfoModel.setUserHeadPortraitUrl(userInfo.getHeadPortraitUrl());
                            forumInfoModel.setForumSubject("系统自动搜所邢台信息");
                            forumInfoModel.setCreateTime(new Date());
                            forumList.add(forumInfoModel);
                        }

                    }
                }
            }
        }
        return true;
    }

    //获取信息内容  招聘信息
    public boolean getParentUtl02(String path,Integer parentId,ArrayList<ForumInfoModel> forumList) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Document document = httpClientUtil.getDocument(path,new HashMap<>(),new HashMap<>());
        if(null != document){
            Elements bk_blue = document.getElementsByClass("bk_blue");
            String todat = DateUtils4Java8.getCurDateTime("yyyy/MM/dd");
            if(null != bk_blue){
                //获取用户信息
                UserModel userInfo = loginMapper.getUserInfo(UserModel.builder()
                        .userId(4).build());
                for (int i = 1; i < bk_blue.size() ; i++) {
                    Element element = bk_blue.get(i);
                    if(null != element){
                        ForumInfoModel forumInfoModel = new ForumInfoModel();
                        //查询是否是今天的
                        Elements zi12_black = element.getElementsByClass("zi12_black");
                        if(null != zi12_black && zi12_black.size()>0){
                            Element element1 = zi12_black.get(0);
                            String substring = element1.ownText().substring(0, 10).trim();
                            if(substring.length() != 10){
                                StringBuffer sb = new StringBuffer();
                                substring = sb.append(substring).insert(5,"0").toString();
                            }
                            if(!todat.equals(substring)){
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
                            forumInfoModel.setForumParentId(parentId);
                            forumInfoModel.setUserHeadPortraitUrl(userInfo.getHeadPortraitUrl());
                            forumInfoModel.setForumSubject("系统自动搜所邢台信息");
                            forumInfoModel.setCreateTime(new Date());
                            forumList.add(forumInfoModel);
                        }

                    }
                }
            }
        }
        return true;
    }

    //获取信息内容
    public boolean getParentUtl03(String path,Integer parentId,ArrayList<ForumInfoModel> forumList) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Document document = httpClientUtil.getDocument(path,new HashMap<>(),new HashMap<>());
        if(null != document){
            Elements bk_blue = document.getElementsByClass("bk_blue");
            String todat = DateUtils4Java8.getCurDateTime("MM.dd");
            if(null != bk_blue){
                //获取用户信息
                UserModel userInfo = loginMapper.getUserInfo(UserModel.builder()
                        .userId(4).build());
                for (int i = 1; i < bk_blue.size() ; i++) {
                    Element element = bk_blue.get(i);
                    if(null != element){
                        ForumInfoModel forumInfoModel = new ForumInfoModel();
                        //获取信息
                        Elements zi12_black = element.getElementsByClass("zi12_black");
                        if(null != zi12_black && zi12_black.size()>0){
                            Element element1 = zi12_black.get(0);
                            if(!todat.equals(element1.ownText())){
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
                            forumInfoModel.setForumParentId(parentId);
                            forumInfoModel.setUserHeadPortraitUrl(userInfo.getHeadPortraitUrl());
                            forumInfoModel.setForumSubject("系统自动搜所邢台信息");
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
