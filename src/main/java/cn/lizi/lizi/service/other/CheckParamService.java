package cn.lizi.lizi.service.other;

import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.mapper.LoginMapper;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.UserCollectModel;
import cn.lizi.lizi.model.other.UserModel;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CheckParamService {

    @Autowired
    ForumMapper forumMapper;
    @Autowired
    LoginMapper loginMapper;

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

    /**
     * 用户注册参数校验
     * @param model
     * @return
     */
    public String checkRegisterParam(UserModel model,HttpServletRequest request) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getNickName()){
            return "用户昵称空";
        }
        if(null == model.getPhone()){
            return "手机号码空";
        }
        if(null == model.getPassword()){
            return "密码空";
        }
        if(null == model.getVerifyNo()){
            return "验证码错误";
        }

        //校验验证码
        String key = "REGISTERPHONE" + model.getPhone();
        String valueCode = (String) cn.lizi.lizi.utils.SessionUtils.getAttr(request, key);
        if(null == valueCode || !valueCode.equals(model.getVerifyNo())){
            return "验证码校验失败";
        }

        //查询手机号或昵称是否存在
        UserModel userInfo = loginMapper.getUserInfo(UserModel.builder()
                .phone(model.getPhone())
                .build());
        if(null != userInfo){
            return "用户已存在";
        }

        //确认密码
        if(!model.getPassword().equals(model.getPassword1())){
            return "两次密码输入不一致";
        }

        return null;
    }


    public String checkLoginParam(UserModel model) {
        if(null == model){
            return "参数空";
        }
        if(null == model.getPhone()){
            return "手机号码空";
        }
        if(null == model.getPassword()){
            return "密码空";
        }



        return null;
    }
}
