package cn.lizi.lizi.model.index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* 描述：333333模型
*
* @author w
* @date 2019-06-15 13:06:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopAdvertisement {

    //主键
    private Integer id;
    //图片01
    private String advertisementUrlA;
    //图片02
    private String advertisementUrlB;
    //广告语01
    private String advertisementHeader;
    //广告语02
    private String advertisementCount;
    //顺序
    private Integer sequence;
    //状态 （1 启用 2 过期）
    private Integer status;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;


}