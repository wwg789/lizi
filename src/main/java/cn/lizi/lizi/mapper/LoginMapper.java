package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.other.UserModel;

public interface LoginMapper {

    int register(UserModel model);

    UserModel getUserInfo(UserModel model);
}
