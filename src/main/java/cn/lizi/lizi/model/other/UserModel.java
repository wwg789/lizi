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
* @date 2019-06-15 13:45:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {


    //主键ID
    private Long id;

    //昵称
    private String nickName;

    //性别 1男 2女
    private Integer gender;

    //头像地址
    private String headPortraitUrl;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}