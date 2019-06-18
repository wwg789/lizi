package cn.lizi.lizi.service.index;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.index.TopAdvertisement;

public interface IndexService {

    ResultModel queryTopAdvertisementList(TopAdvertisement model);


}
