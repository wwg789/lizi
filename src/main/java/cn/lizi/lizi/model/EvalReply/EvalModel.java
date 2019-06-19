package cn.lizi.lizi.model.EvalReply;

import cn.lizi.lizi.model.common.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 描述：评论
 *
 * @author w
 * @date 2019-06-19 14:36:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalModel extends CommonModel {

    //主键ID
    private Long id;
    //主题ID
    private Long forumInfoId;
    //用户ID
    private Integer userId;
    //用户头像url
    private String userHeadUrl;
    //用户昵称
    private String userNickName;
    //评价内容
    private String evalContent;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //回复列表
    private List<ReplyModel> replyList;
}
