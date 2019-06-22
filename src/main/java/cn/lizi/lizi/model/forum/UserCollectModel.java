package cn.lizi.lizi.model.forum;

import cn.lizi.lizi.model.common.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述：用户收藏
 * @author w
 * @date 2019-06-20 16:34:20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCollectModel extends CommonModel {

    //主键ID
    private Integer id;
    //用户ID
    private Integer userId;
    //发帖ID
    private Integer forumId;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
