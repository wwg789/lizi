package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.forum.ForumInfoModel;

import java.util.List;

public interface ForumMapper {

    //主题列表查询
    List<ForumInfoModel> queryForumList(ForumInfoModel model);

    //
    ForumInfoModel queryForumDetail(ForumInfoModel model);

    //
    int updateForum(ForumInfoModel model);

    //
    int addForum(ForumInfoModel model);
}
