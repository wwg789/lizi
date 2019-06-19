package cn.lizi.lizi.controller;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.service.forum.ForumService;
import com.sun.deploy.net.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 论坛
 */
@Slf4j
@Controller
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    ForumService forumService;

    /**
     * 主题列表查询
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumList")
    public ResultModel queryForumList(ForumInfoModel model){
        return forumService.queryForumList(model);
    }

    /**
     * 主题列表最新
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumListByZuiXin")
    public ResultModel queryForumListByZuiXin(ForumInfoModel model){
        return forumService.queryForumListByZuiXin(model);
    }

    /**
     * 主题列表热门
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumListByReMen")
    public ResultModel queryForumListByReMen(ForumInfoModel model){
        return forumService.queryForumListByReMen(model);
    }

    /**
     * 主题列表热门
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumListByJingHua")
    public ResultModel queryForumListByJingHua(ForumInfoModel model){
        return forumService.queryForumListByJingHua(model);
    }

    /**
     * 主题详情查询
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumDetail")
    public ResultModel queryForumDetail(ForumInfoModel model){
        return forumService.queryForumDetail(model);
    }

    /**
     * 主题详情修改
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateForum")
    public ResultModel updateForum(ForumInfoModel model){
        return forumService.updateForum(model);
    }

    /**
     * 主题新增
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/addForum")
    public ResultModel addForum(ForumInfoModel model){
        return forumService.addForum(model);
    }

    /**
     * 获取父级分类列表
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryForumParentList")
    public ResultModel queryForumParentList(ForumParentDetailModel model){
        return forumService.queryForumParentList(model);
    }



}
