package cn.lizi.lizi.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/luntanindex")
    public String luntanindex(@Param("id") String id, HttpServletResponse response) {
        response.addCookie(new Cookie("parentId",id));
        return "luntanindex";
    }

    @RequestMapping("/write")
    public String write(@Param("parentId") String parentId, HttpServletResponse response) {
        response.addCookie(new Cookie("parentId",parentId));
        return "write";
    }

    @RequestMapping("/lt_content")
    public String lt_content(@Param("forumId") String forumId, HttpServletResponse response) {
        response.addCookie(new Cookie("forumId",forumId));
        return "lt_content";
    }

    @RequestMapping("/me")
    public String lt_content(HttpServletResponse response) {
        //response.addCookie(new Cookie("forumId",forumId));
        return "me";
    }

    @RequestMapping("/myWrite")
    public String myWrite(HttpServletResponse response) {
        //response.addCookie(new Cookie("forumId",forumId));
        return "myWrite";
    }

    @RequestMapping("/myCelect")
    public String myCelect(HttpServletResponse response) {
        //response.addCookie(new Cookie("forumId",forumId));
        return "myCelect";
    }

}
