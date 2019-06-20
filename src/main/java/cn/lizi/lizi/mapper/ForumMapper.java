package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.forum.UserCollectModel;

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

    //添加用户收藏
    int addCollect(UserCollectModel model);

    //查询用户收藏列表
    List<ForumInfoModel> queryUserCollectList(ForumInfoModel model);
}
