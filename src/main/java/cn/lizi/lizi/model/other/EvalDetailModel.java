package cn.lizi.lizi.model.other;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* 描述：333333模型
*
* @author w
* @date 2019-06-15 13:38:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalDetailModel {


    //主键ID
    private Long id;

    //主题ID
    private Long forumInfoId;

    //评价ID
    private Long forumEvalId;

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
}