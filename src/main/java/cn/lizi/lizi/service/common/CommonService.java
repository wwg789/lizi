package cn.lizi.lizi.service.common;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.common.CommonModel;
import cn.lizi.lizi.model.other.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface CommonService {

    /**
     * 设置分页查询条件
     *
     * @param model 结果
     */
    void setQueryPage(CommonModel model);

}
