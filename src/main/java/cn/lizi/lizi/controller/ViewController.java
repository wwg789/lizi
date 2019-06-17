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
    public String write(@Param("id") String id, HttpServletResponse response) {
        response.addCookie(new Cookie("parentId",id));
        return "write";
    }

}
