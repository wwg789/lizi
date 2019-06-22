package cn.lizi.lizi.controller;

import cn.lizi.lizi.ZiDingYiZhuJie.authority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/index")
    public String index(@Param("token") String token, HttpServletResponse response) {
        response.addCookie(new Cookie("token",token));
        return "index";
    }

    @RequestMapping("/luntanindex")
    public String luntanindex(@Param("id") String id, HttpServletResponse response) {
        response.addCookie(new Cookie("parentId",id));
        return "luntanindex";
    }
    @authority
    @RequestMapping("/write")
    public String write(@Param("parentId") String parentId,@Param("token")String token, HttpServletResponse response) {
        response.addCookie(new Cookie("parentId",parentId));
        response.addCookie(new Cookie("token",token));
        return "write";
    }

    @RequestMapping("/lt_content")
    public String lt_content(@Param("forumId") String forumId, HttpServletResponse response) {
        response.addCookie(new Cookie("forumId",forumId));
        return "lt_content";
    }
    @authority
    @RequestMapping("/me")
    public String me(@Param("token") String token, HttpServletResponse response) {
        response.addCookie(new Cookie("token",token));
        return "me";
    }
    @authority
    @RequestMapping("/myWrite")
    public String myWrite(@Param("token") String token, HttpServletResponse response) {
        response.addCookie(new Cookie("token",token));
        return "myWrite";
    }
    @authority
    @RequestMapping("/myCelect")
    public String myCelect(@Param("token") String token, HttpServletResponse response) {
        response.addCookie(new Cookie("token",token));
        return "myCelect";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

}
