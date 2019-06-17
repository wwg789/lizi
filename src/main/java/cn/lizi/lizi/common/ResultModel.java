package cn.lizi.lizi.common;

import cn.lizi.lizi.model.common.CommonModel;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 公共返回实体
 */
@Data
public class ResultModel implements java.io.Serializable {

    private Object data;
    private String msg;
    private Integer code;

    private Integer page;
    private Integer pages;
    private Integer pageSize;
    private Long count;


    public static ResultModel getResult(Integer code, String msg, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(code);
        resultModel.setData(data);
        resultModel.setMsg(msg);
        return resultModel;
    }

    public static ResultModel getSuccess(String msg, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(data);
        resultModel.setMsg(msg);
        return resultModel;
    }

    public static ResultModel getError(String msg) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(999);
        resultModel.setData(null);
        resultModel.setMsg(msg);
        return resultModel;
    }

    public static ResultModel getPageData(String msg, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(data);
        resultModel.setMsg(msg);

        if (null != data && data instanceof List) {
            return orgResultPages(resultModel, (List) data);
        }
        return resultModel;
    }

    /**
     * 设置ResultModel返回页码属性
     *
     * @param result ResultModel
     * @param list   数据集
     * @return ResultModel
     */
    public static ResultModel orgResultPages(ResultModel result, List list) {
        PageInfo pageInfo = new PageInfo(list);
        result.setPages(pageInfo.getPages());
        result.setCount(pageInfo.getTotal());
        return result;
    }


}
