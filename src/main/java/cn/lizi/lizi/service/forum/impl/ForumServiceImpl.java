package cn.lizi.lizi.service.forum.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.service.common.impl.CommonServiceImpl;
import cn.lizi.lizi.service.forum.ForumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ForumServiceImpl extends CommonServiceImpl implements ForumService {

    @Autowired()
    ForumMapper forumMapper;

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
        setQueryPage(model);
        List<ForumInfoModel> forumList = forumMapper.queryForumList(model);
        return ResultModel.getSuccess("成功",forumList);
    }

    /**
     *获取帖子详情
     * @param model
     * @return
     */
    @Override
    public ResultModel queryForumDetail(ForumInfoModel model){
        log.info("getForumInfo params {}",model);
        ForumInfoModel forumInfo = forumMapper.queryForumDetail(model);
        ResultModel 成功 = ResultModel.getSuccess("成功", forumInfo);
        return 成功;
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
        int reoult = forumMapper.addForum(model);
        if(reoult < 1){
            return ResultModel.getError("创建失败");
        }
        return ResultModel.getSuccess("成功",null);
    }

}
