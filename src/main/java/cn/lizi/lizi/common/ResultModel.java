package cn.lizi.lizi.common;

import cn.lizi.lizi.model.common.CommonModel;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
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
    private Integer pageSize;
    private Integer count;


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

    public static ResultModel getPage(String msg, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(data);
        resultModel.setMsg(msg);

        if(null != data){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(data));

            resultModel.setPage(Integer.valueOf(jsonObject.get("page").toString()));
            resultModel.setPageSize(Integer.valueOf(jsonObject.get("pageSize").toString()));
            resultModel.setCount(Integer.valueOf(jsonObject.get("count").toString()));
        }
        return resultModel;
    }


}
