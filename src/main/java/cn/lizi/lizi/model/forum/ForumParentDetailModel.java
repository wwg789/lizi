package cn.lizi.lizi.model.forum;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述：父级分类model
 *
 * @author wwg
 * @date 2019-06-17 14:57:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumParentDetailModel {

    //主键ID
    private Integer id;

    //主题分类名称
    private String forumParentName;

    //创建时间
    private Date cerateTime;

    //修改时间
    private Date updateTime;
}
