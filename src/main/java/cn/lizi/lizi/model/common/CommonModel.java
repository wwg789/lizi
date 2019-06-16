package cn.lizi.lizi.model.common;

import lombok.Data;

/**
 * 公共Model
 */
@Data
public class CommonModel implements java.io.Serializable {

    private static final long serialVersionUID = -3310371598185923544L;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Integer count;

}
