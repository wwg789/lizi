package cn.lizi.lizi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 论坛
 */
@Slf4j
@Controller
@RequestMapping("/forum")
public class ForumController {

    /**
     * 微信回调 获取用户信息
     * @param model
     * @return
     */
    @RequestMapping("/getUserInfo")
    public String getUserInfo(Object model){

        return "null";
    }
}
