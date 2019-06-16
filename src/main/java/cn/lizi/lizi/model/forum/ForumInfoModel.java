package cn.lizi.lizi.model.forum;

import cn.lizi.lizi.model.common.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* 描述：333333模型
*
* @author w
* @date 2019-06-15 13:33:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumInfoModel extends CommonModel implements Serializable {


    //主键
    private String id;

    //用户ID
    private Integer userId;

    //用户昵称
    private String nickName;

    //用户头像地址
    private String userHeadPortraitUrl;

    //主题父级ID
    private Integer forunParentId;

    //论坛主题
    private String forumSubject;

    //论坛内容
    private String forumContent;

    //主题收藏数量
    private Integer forumCollectCount;

    //主题评价次数
    private Integer forumEvaluateCount;

    //主题查看次数
    private Integer forumSelectCount;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}