package cn.lizi.lizi.model.advertisement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* 描述：333333模型
*
* @author w
* @date 2019-06-15 13:08:15
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexAdvertisementModel {


    //主键
    private Integer id;

    //图片01
    private String advertisementUrl1;

    //图片02
    private String advertisementUrl2;

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