package cn.lizi.lizi.service.other;

import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.UserCollectModel;
import cn.lizi.lizi.model.other.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CheckParamService {

    @Autowired
    ForumMapper forumMapper;

    public String checkAddForum(ForumInfoModel model){
        if(null == model){
            return "参数空";
        }
        if(null == model.getForumParentId()){
            return "分类ID空";
        }
        if(StringUtils.isEmpty(model.getForumSubject())){
            return "主题空";
        }
        if(StringUtils.isEmpty(model.getForumContent())){
            return "主题内容空";
        }
        return null;
    }



    public String checkQueryEvalDetailParam(EvalModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getForumInfoId()){
            return "主题ID空";
        }
        return null;
    }

    public String checkAddEvalDetail(EvalModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getForumInfoId()){
            return "主题ID空";
        }
        if(StringUtils.isEmpty(model.getEvalContent())){
            return "评价内容空";
        }

        return null;

    }

    public String checkAddReplyDetail(ReplyModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getForumEvalId()){
            return "评价ID空";
        }
        if(StringUtils.isEmpty(model.getReplyContent())){
            return "回复内容空";
        }

        return null;

    }

    public String checkQueryForumDetail(ForumInfoModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getId()){
            return "发帖ID空";
        }

        return null;

    }

    public String checkAddCollect(UserCollectModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getForumId()){
            return "发帖ID空";
        }

        //TODO  此部分用户信息从token中获取
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setGender(1);
        userModel.setHeadPortraitUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3768890033,68770272&fm=27&gp=0.jpg");
        userModel.setNickName("栗哥的大树");
        model.setUserId(userModel.getId());

        UserCollectModel userCollectModel = forumMapper.queryUserIsCollect(model);
        if(null != userCollectModel){
            return "已经收藏过此贴";
        }

        return null;
    }
}
