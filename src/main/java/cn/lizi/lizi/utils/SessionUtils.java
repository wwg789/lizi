package cn.lizi.lizi.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * Session 工具类
 *
 */
public final class SessionUtils {

    /**
      * 设置session的值
      * @param request
      * @param key
      * @param value
      */
     public static void setAttr(HttpServletRequest request,String key,Object value){
         HttpSession session = request.getSession(true);
         //以秒为单位，即在没有活动30分钟后，session将失效
         session.setMaxInactiveInterval(30*60);
         session.setAttribute(key, value);
     }
     
     
     /**
      * 获取session的值
      * @param request
      * @param key
      */
     public static Object getAttr(HttpServletRequest request,String key){
         HttpSession session = request.getSession(true);
         //以秒为单位，即在没有活动30分钟后，session将失效
         session.setMaxInactiveInterval(30*60);
         return session.getAttribute(key);
     }
     
     /**
      * 删除Session值
      * @param request
      * @param key
      */
     public static void removeAttr(HttpServletRequest request,String key){
         request.getSession(true).removeAttribute(key);
     }



    
}