package cn.lizi.lizi.model.forum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* 描述：333333模型
*
* @author w
* @date 2019-06-15 13:29:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForunParantInfoModel {


    //主键ID
    private Integer id;

    //主题分类名称
    private String forumParentName;

    //创建时间
    private Date cerateTime;

    //修改时间
    private Date updateTime;
}