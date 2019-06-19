package cn.lizi.lizi.service.evalReply.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.EvalReplyMapper;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.common.impl.CommonServiceImpl;
import cn.lizi.lizi.service.evalReply.EvalReplyService;
import cn.lizi.lizi.service.other.CheckParamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvalReplyServiceImpl  extends CommonServiceImpl implements EvalReplyService {

    @Autowired
    EvalReplyMapper evalReplyMapper;
    @Autowired
    CheckParamService checkParamService;

    /**
     * 新增 评价
     * @param model
     * @return
     */
    @Override
    public ResultModel addEvalDetail(EvalModel model) {
        String resout = checkParamService.checkAddEvalDetail(model);
        if(StringUtils.isNotEmpty(resout)){
            ResultModel.getError(resout);
        }
        //TODO 获取用户信息  这里先创建一个
        UserModel user = new UserModel();
        user.setId(1);
        user.setNickName("栗哥的大树");
        user.setHeadPortraitUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3768890033,68770272&fm=27&gp=0.jpg");
        user.setGender(1);

        model.setUserId(user.getId());
        model.setUserHeadUrl(user.getHeadPortraitUrl());
        model.setUserNickName(user.getNickName());
        model.setCreateTime(new Date());

        int resoutData = evalReplyMapper.addEvalDetail(model);

        if(resoutData < 1){
           return  ResultModel.getError("评价失败  网络错误");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     * 新增 回复
     * @param model
     * @return
     */
    @Override
    public ResultModel addReplyDetail(ReplyModel model) {

        String resout = checkParamService.checkAddReplyDetail(model);
        if(StringUtils.isNotEmpty(resout)){
            ResultModel.getError(resout);
        }

        //TODO 获取用户信息  这里先创建一个
        UserModel user = new UserModel();
        user.setId(1);
        user.setNickName("栗哥的大树");
        user.setHeadPortraitUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3768890033,68770272&fm=27&gp=0.jpg");
        user.setGender(1);

        model.setUserId(user.getId());
        model.setUserHeadUrl(user.getHeadPortraitUrl());
        model.setUserNickName(user.getNickName());
        model.setCreateTime(new Date());


        int resoutData = evalReplyMapper.addReplyDetail(model);
        if(resoutData < 1){
            return  ResultModel.getError("回复失败  网络错误");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     * 查询发帖评价列表
     * @param model
     * @return
     */
    @Override
    public ResultModel queryEvalDetail(EvalModel model) {
        String resout = checkParamService.checkQueryEvalDetailParam(model);
        if(StringUtils.isNotEmpty(resout)){
            ResultModel.getError(resout);
        }

        setQueryPage(model);
        List<EvalModel> resoutList = evalReplyMapper.queryEvalList(model);

        //获取回复
        resoutList.forEach(k->{
           k.setReplyList(queryReplyDetail(k.getId()));
        });
        return ResultModel.getPageData("成功",resoutList);
    }

    /**
     * 查询评价的回复列表
     * @param evalId
     * @return
     */
    public List<ReplyModel> queryReplyDetail(Long evalId) {
        if(null == evalId)return null;
        return evalReplyMapper.queryReplyList(ReplyModel.builder()
                .forumEvalId(evalId)
                .build());
    }




}
