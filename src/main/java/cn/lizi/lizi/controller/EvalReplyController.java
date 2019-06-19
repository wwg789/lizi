package cn.lizi.lizi.controller;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.EvalReply.EvalModel;
import cn.lizi.lizi.model.EvalReply.ReplyModel;
import cn.lizi.lizi.service.evalReply.EvalReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评价回复
 */
@Slf4j
@Controller
@RequestMapping("/evalReply")
public class EvalReplyController {

    @Autowired
    EvalReplyService evalReplyService;

    /**
     * 新增评价
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/addEvalDetail")
    public ResultModel addEvalDetail(EvalModel model){
        return evalReplyService.addEvalDetail(model);
    }

    /**
     * 新增回复
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/addReplyDetail")
    public ResultModel addReplyDetail(ReplyModel model){
        return evalReplyService.addReplyDetail(model);
    }

    /**
     * 评价内容查询
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryEvalList")
    public ResultModel queryEvalList(EvalModel model){
        return evalReplyService.queryEvalList(model);
    }




}
