package cn.lizi.lizi.service.index.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.IndexMapper;
import cn.lizi.lizi.model.index.TopAdvertisement;
import cn.lizi.lizi.service.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexMapper indexMapper;

    @Override
    public ResultModel queryTopAdvertisementList(TopAdvertisement model) {
        return ResultModel.getSuccess("成功",indexMapper.queryTopAdvertisementList(model));
    }
}
