package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;

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

    List<ForumParentDetailModel> queryForumParentList();

    List<ForumInfoModel> queryForumListZuiXin(ForumInfoModel model);

    List<ForumInfoModel> queryForumListReMen(ForumInfoModel model);

    List<ForumInfoModel> queryForumListJingHua(ForumInfoModel model);

    //修改统计次数
    int updateForumSelectCount();
    int updateCollectCount();
    int updateForumEvalCount();
}
