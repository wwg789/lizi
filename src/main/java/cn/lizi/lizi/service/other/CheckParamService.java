package cn.lizi.lizi.service.other;

import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CheckParamService {

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
}
