package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.index.TopAdvertisement;

import java.util.List;

public interface IndexMapper {

    List<TopAdvertisement> queryTopAdvertisementList(TopAdvertisement model);
}
