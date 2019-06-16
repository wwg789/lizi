package cn.lizi.lizi.controller;

import cn.lizi.lizi.common.ResultModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class indexController {

    public String index() {
        return "index.html";
    }


    public String getAdvertisement01() {
        return null;
    }


    /**
     * 获取分类信息
     * @return
     */
    public ResultModel getClassificationInformation() {
        return null;
    }

}
