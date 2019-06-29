package cn.lizi.lizi.service.evalReply.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.EvalReplyMapper;
import cn.lizi.lizi.mapper.ForumMapper;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.common.impl.CommonServiceImpl;
import cn.lizi.lizi.service.evalReply.EvalReplyService;
import cn.lizi.lizi.service.other.CheckParamService;
import cn.lizi.lizi.utils.DateUtils4Java8;
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
    @Autowired
    ForumMapper forumMapper;


    /**
     * 新增 评价
     * @param model
     * @return
     */
    @Override
    public ResultModel addEvalDetail(EvalModel model) {
        String resout = checkParamService.checkAddEvalDetail(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        model.setUserId(model.getUser().getId());
        model.setUserHeadUrl(model.getUser().getHeadPortraitUrl());
        model.setUserNickName(model.getUser().getNickName());
        model.setCreateTime(new Date());

        int resoutData = evalReplyMapper.addEvalDetail(model);

        //修改评价次数统计
        forumMapper.updateForumEvalCount(ForumInfoModel.builder().id(model.getForumInfoId().toString()).build());

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
            return ResultModel.getError(resout);
        }

        model.setUserId(model.getUser().getId());
        model.setUserHeadUrl(model.getUser().getHeadPortraitUrl());
        model.setUserNickName(model.getUser().getNickName());
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
    public ResultModel queryEvalList(EvalModel model) {
        String resout = checkParamService.checkQueryEvalDetailParam(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        setQueryPage(model);
        List<EvalModel> resoutList = evalReplyMapper.queryEvalList(model);

        //获取回复,计算创建时间差
        resoutList.forEach(k->{
           k.setReplyList(queryReplyDetail(k.getId()));
            try {
                k.setChaTime(DateUtils4Java8.getTimeDiff(new Date(), k.getCreateTime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        List<ReplyModel> replyModels = evalReplyMapper.queryReplyList(ReplyModel.builder()
                .forumEvalId(evalId)
                .build());
        if(null != replyModels && replyModels.size() > 0){
            replyModels.forEach(k->{
                try {
                    k.setChaTime(DateUtils4Java8.getTimeDiff(new Date(), k.getCreateTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return replyModels;
    }




}
