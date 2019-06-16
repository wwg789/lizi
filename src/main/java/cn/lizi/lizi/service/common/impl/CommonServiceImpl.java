package cn.lizi.lizi.service.common.impl;

import cn.lizi.lizi.model.common.CommonModel;
import cn.lizi.lizi.service.common.CommonService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;


@Service
public class CommonServiceImpl implements CommonService {

    /**
     * 设置分页查询条件
     *
     * @param model 结果
     */
    public void setQueryPage(CommonModel model) {
        if (null != model.getPage() && null != model.getPageSize())
            PageHelper.startPage(model.getPage(), model.getPageSize());
    }

}
