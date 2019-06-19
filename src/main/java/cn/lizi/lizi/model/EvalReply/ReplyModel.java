package cn.lizi.lizi.model.EvalReply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述：评价 的 回复
 * @author w
 * @date 2019-06-19 15:13:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyModel {

    //主键ID
    private Long id;
    //评论ID
    private Long forumEvalId;
    //用户ID
    private Integer userId;
    //用户头像url
    private String userHeadUrl;
    //用户昵称
    private String userNickName;
    //回复内容
    private String replyContent;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
