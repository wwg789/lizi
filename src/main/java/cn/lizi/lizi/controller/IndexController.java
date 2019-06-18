package cn.lizi.lizi.controller;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.index.TopAdvertisement;
import cn.lizi.lizi.service.index.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 */
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    IndexService indexService;

    /**
     * 获取首页顶部广告列表
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryTopAdvertisementList")
    public ResultModel queryForumParentList(TopAdvertisement model){
        return indexService.queryTopAdvertisementList(model);
    }


}
