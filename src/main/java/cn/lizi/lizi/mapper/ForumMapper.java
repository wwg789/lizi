package cn.lizi.lizi.mapper;

import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.forum.UserCollectModel;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface ForumMapper {

    //主题列表查询
    List<ForumInfoModel> queryForumList(ForumInfoModel model);

    List<ForumInfoModel> queryForumListZuiXin(ForumInfoModel model);

    List<ForumInfoModel> queryForumListReMen(ForumInfoModel model);

    List<ForumInfoModel> queryForumListJingHua(ForumInfoModel model);

    //获取父级分类列表
    List<ForumParentDetailModel> queryForumParentList();

    //修改统计次数
    int updateForumSelectCount();
    int updateCollectCount();
    int updateForumEvalCount();

    //添加用户收藏
    int addCollect(UserCollectModel model);

    //查询用户收藏列表
    List<ForumInfoModel> queryUserCollectList(ForumInfoModel model);

    //查询发帖详情
    ForumInfoModel queryForumDetail(ForumInfoModel model);

    //修改发帖详情
    int updateForum(ForumInfoModel model);

    //发帖
    int addForum(ForumInfoModel model);

    //查询是否收藏此帖
    UserCollectModel queryUserIsCollect(UserCollectModel model);

    void addForumList(@Param("list") ArrayList<ForumInfoModel> forumList);
}
