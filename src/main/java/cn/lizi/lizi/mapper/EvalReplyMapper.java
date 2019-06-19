package cn.lizi.lizi.mapper;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EvalReplyMapper {

    //新增评价
    int addEvalDetail(EvalModel model);

    //评价列表
    List<EvalModel> queryEvalList(EvalModel model);

    //新增回复
    int addReplyDetail(ReplyModel model);

    //查询评价的回复 列表
    List<ReplyModel> queryReplyList(ReplyModel model);
}
