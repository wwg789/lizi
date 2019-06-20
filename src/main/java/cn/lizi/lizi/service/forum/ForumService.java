package cn.lizi.lizi.service.forum;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.forum.ForumInfoModel;
import cn.lizi.lizi.model.forum.ForumParentDetailModel;
import cn.lizi.lizi.model.forum.UserCollectModel;

import javax.servlet.http.HttpServletRequest;

public interface ForumService{

    ResultModel queryForumList(ForumInfoModel model);

    ResultModel queryForumDetail(ForumInfoModel model);

    ResultModel updateForum(ForumInfoModel model);

    ResultModel addForum(ForumInfoModel model);

    ResultModel queryForumParentList(ForumParentDetailModel model);

    ResultModel queryForumListByZuiXin(ForumInfoModel model);

    ResultModel queryForumListByReMen(ForumInfoModel model);

    ResultModel queryForumListByJingHua(ForumInfoModel model);

    ResultModel addCollect(UserCollectModel model);

    ResultModel queryUserCollectList(ForumInfoModel model);
}
