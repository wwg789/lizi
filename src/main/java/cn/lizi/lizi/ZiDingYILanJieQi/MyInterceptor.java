package cn.lizi.lizi.ZiDingYILanJieQi;

import cn.lizi.lizi.ZiDingYiZhuJie.authority;
import cn.lizi.lizi.utils.JwtTokenUtil;
import com.auth0.jwt.interfaces.Claim;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * 自定义拦截器
 */
@Component
public class MyInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();



        //如果方法未注明@authority
        if (method.isAnnotationPresent(authority.class)) {
            Cookie[] cookies = request.getCookies();
            String token = "";
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if("token".equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
            if(StringUtils.isEmpty(token)){
                return false;
            }
            try {
                //校验token
                Map<String, Claim> stringClaimMap = JwtTokenUtil.verifyToken(token);
                stringClaimMap.get("userId").asString();
                Map<String, String[]> parameterMap = request.getParameterMap();
                parameterMap.forEach((key,val)->{
                    System.out.print("key="+key+"  value="+val);
                });

            }catch (Exception e){
                return false;
            }

            return true;
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
        System.out.print("在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
        System.out.print("在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面），可以根据ex是否为null判断是否发生了异常，进行日志记录");
    }

}