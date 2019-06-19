package cn.lizi.lizi.service.forum.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.common.impl.CommonServiceImpl;
import cn.lizi.lizi.service.forum.ForumService;
import cn.lizi.lizi.service.other.CheckParamService;
import cn.lizi.lizi.utils.DateUtils4Java8;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ForumServiceImpl extends CommonServiceImpl implements ForumService {

    @Autowired()
    ForumMapper forumMapper;
    @Autowired
    CheckParamService checkParamService;

    /**
     * 获取发帖列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumList(ForumInfoModel model) {
        log.info("getForumList params {}",model);
        if(null == model.getPage()){
            model.setPage(0);
        }
        if(null == model.getPageSize()){
            model.setPageSize(10);
        }

        List<ForumInfoModel> forumList = new ArrayList<>();
        //最新
        if(model.getTemp() == 0){
            setQueryPage(model);
            forumList = forumMapper.queryForumListZuiXin(model);
        }
        //热门
        else if (model.getTemp() == 1){
            setQueryPage(model);
            forumList = forumMapper.queryForumListReMen(model);
        }
        //精华
        else if (model.getTemp() == 2){
            setQueryPage(model);
            forumList = forumMapper.queryForumListJingHua(model);
        }
        forumList.forEach(k->{
            try {
                k.setChaTime(DateUtils4Java8.getTimeDiff(new Date(), k.getCreateTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResultModel.getPageData("成功",forumList);
    }

    /**
     *获取帖子详情
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumDetail(ForumInfoModel model){
        String resout = checkParamService.checkQueryForumDetail(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }
       log.info("getForumInfo params {}",model);
        ForumInfoModel forumInfo = forumMapper.queryForumDetail(model);
        return ResultModel.getSuccess("成功", forumInfo);
    }

    /**
     *修改帖子详情
     * @param model
     * @return
     */
    @Override
    public ResultModel updateForum(ForumInfoModel model) {
        int reoult = forumMapper.updateForum(model);
        if(reoult < 1){
            return ResultModel.getError("修改失败");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     *新建帖子
     * @param model
     * @return
     */
    @Override
    public ResultModel addForum(ForumInfoModel model) {

        String resout = checkParamService.checkAddForum(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }
        //TODO  此部分用户信息从token中获取
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setGender(1);
        userModel.setHeadPortraitUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3768890033,68770272&fm=27&gp=0.jpg");
        userModel.setNickName("栗哥的大树");
        model.setUser(userModel);

        //初始参数
        model.setUserId(model.getUser().getId());
        model.setNickName(model.getUser().getNickName());
        model.setUserHeadPortraitUrl(model.getUser().getHeadPortraitUrl());
        model.setCreateTime(new Date());

        int reoult = forumMapper.addForum(model);
        if(reoult < 1){
            return ResultModel.getError("创建失败");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     *获取父级分类列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumParentList(ForumParentDetailModel model){
        return ResultModel.getSuccess("成功",forumMapper.queryForumParentList());
    }

    @Override
    public ResultModel queryForumListByZuiXin(ForumInfoModel model) {
        return null;
    }

    @Override
    public ResultModel queryForumListByReMen(ForumInfoModel model) {
        return null;
    }

    @Override
    public ResultModel queryForumListByJingHua(ForumInfoModel model) {
        return null;
    }

}
