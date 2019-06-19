package cn.lizi.lizi.service.evalReply;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;

public interface EvalReplyService {

    ResultModel queryEvalList(EvalModel model);

    ResultModel addReplyDetail(ReplyModel model);

    ResultModel addEvalDetail(EvalModel model);
}
