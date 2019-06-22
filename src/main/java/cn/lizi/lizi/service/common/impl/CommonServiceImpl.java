package cn.lizi.lizi.service.common.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.LoginMapper;
import cn.lizi.lizi.model.common.CommonModel;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.common.CommonService;
import cn.lizi.lizi.utils.JwtTokenUtil;
import cn.lizi.lizi.utils.SMSUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    LoginMapper loginMapper;
    //设置分页查询条件
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

    public boolean initUserInfo(CommonModel model) {
        //token中封装用户信息
        try {
            Integer userId = JwtTokenUtil.verifyToken(model.getToken()).get("userId").asInt();
            if(null != userId){
                UserModel userInfo = loginMapper.getUserInfo(UserModel.builder()
                        .userId(userId).build());
                model.setUser(userInfo);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
