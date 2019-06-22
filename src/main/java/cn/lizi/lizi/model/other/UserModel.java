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
    private Integer id;
    //昵称
    private String nickName;
    //性别 1男 2女
    private Integer gender;
    //头像地址
    private String headPortraitUrl;
    //手机号
    private String phone;
    //密码
    private String password;
    //确认密码
    private String password1;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //验证码
    private String verifyNo;
    private String token;
    private Integer userId;
}