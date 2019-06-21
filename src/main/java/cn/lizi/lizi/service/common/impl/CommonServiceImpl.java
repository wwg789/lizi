package cn.lizi.lizi.service.common.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.model.common.CommonModel;
import cn.lizi.lizi.service.common.CommonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommonServiceImpl implements CommonService {

    /**
     * 设置分页查询条件
     *
     * @param model 结果
     */
    public void setQueryPage(CommonModel model) {
        if (null != model.getPage() && null != model.getPageSize())
            PageHelper.startPage(model.getPage(), model.getPageSize());
    }

    //计算文件保存路径
    public String getFileSavePath(String timeStr) {
        String Path = "picture-" + timeStr.substring(timeStr.length()-1,timeStr.length());
        return  Path;
    }

    //计算文件获取路径
    public String getFileDownloadPath(String timeStr0) {
        String timeStr = timeStr0.substring(0,timeStr0.length()-4);
        String Path = "picture-" + timeStr.substring(timeStr.length()-1,timeStr.length());
        return  Path;
    }


}
