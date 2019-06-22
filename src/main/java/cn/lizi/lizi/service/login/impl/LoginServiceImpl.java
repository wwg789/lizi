package cn.lizi.lizi.service.login.impl;

import cn.lizi.lizi.common.ResultModel;
import cn.lizi.lizi.mapper.LoginMapper;
import cn.lizi.lizi.model.other.UserModel;
import cn.lizi.lizi.service.login.LoginService;
import cn.lizi.lizi.service.other.CheckParamService;
import cn.lizi.lizi.utils.*;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    CheckParamService checkParamService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    SMSUtil smsUtil;
    @Value("${ali.SMS.register}")
    String registerTem = "";


    //定义用户注册时的Key
    final String REGISTERKEY =  "REGISTERPHONE";

    //获取验证码
    @Override
    public ResultModel sendVerificationCode(UserModel model){
        //校验参数
        if(null == model || null == model.getPhone()){
            return ResultModel.getError("用户手机号空");
        }
        //获取随机数
        String ran = String.valueOf((int)((Math.random()*9+1)*10000));
        String key = REGISTERKEY + model.getPhone();

        //发送
        try {
            SendSmsResponse sendSmsResponse = smsUtil.sendSms(registerTem, model.getPhone(), ran);
            log.info("短信接口返回的数据----------------");
            log.info("Code=" + sendSmsResponse.getCode());
            log.info("Message=" + sendSmsResponse.getMessage());
            log.info("RequestId=" + sendSmsResponse.getRequestId());
            log.info("BizId=" + sendSmsResponse.getBizId());

        } catch (ClientException e) {
            e.printStackTrace();
        }

        //保存session
        SessionUtils.setAttr(request,key,ran);

        return ResultModel.getSuccess("发送成功",null);
    }


    /**
     * 用户注册
     * @param model
     * @return
     */
    @Override
    public ResultModel register(UserModel model) {
        //参数校验
        String resout = checkParamService.checkRegisterParam(model,request);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        //MD5加密   盐  WUWEIGANGSUNLI123.+
        model.setPassword(Md5Utils.encryptPassword(model.getPassword(),"WUWEIGANGSUNLI123.+"));

        //添加
        int ret = loginMapper.register(model);

        if(ret < 1){
            return ResultModel.getError("注册失败，请联系管理员 电话:15194934772 谢谢");
        }
        return ResultModel.getSuccess("成功",null);
    }

    /**
     * 用户登陆
     * @param model
     * @return
     */
    @Override
    public ResultModel login(UserModel model) {
        //参数校验
        String resout = checkParamService.checkLoginParam(model);
        if(StringUtils.isNotEmpty(resout)){
            return ResultModel.getError(resout);
        }

        //查询用户信息
        UserModel userInfo = loginMapper.getUserInfo(UserModel.builder().phone(model.getPhone()).build());
        if(null == userInfo){
            return ResultModel.getError("用户不存在");
        }

        //计算用户密码
        String retPass = Md5Utils.encryptPassword(model.getPassword(), "WUWEIGANGSUNLI123.+");
        if(!retPass.equals(userInfo.getPassword())){
            return ResultModel.getError("密码输入错误 请重试");
        }

        //生成token
        try {
            String token = JwtTokenUtil.createToken(userInfo);
            //放入cokiee
            //response.addCookie(new Cookie("token","1111111111"));
            model.setToken(token);
        } catch (Exception e) {
            log.info("登陆生成token失败");
            e.printStackTrace();
        }
        return ResultModel.getSuccess("成功",model);
    }

    /**
     * 获取用户信息
     * @param model
     * @return
     */
    @Override
    public ResultModel getUserInfo(UserModel model) {
        try {
            Integer userId = JwtTokenUtil.verifyToken(model.getToken()).get("userId").asInt();
            if(null == userId){
                return ResultModel.getError("登陆信息过期请重新登陆");
            }
            model.setId(userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        UserModel userInfo= loginMapper.getUserInfo(model);
        return ResultModel.getSuccess("成功",userInfo);
    }

}
