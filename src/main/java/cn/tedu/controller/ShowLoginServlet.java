package cn.tedu.controller;

import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowLoginServlet",urlPatterns = "/showlogin")
public class ShowLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = new Context();
        ThUtils.print("login.html",context,response);
        //取出cookie内容
        Cookie[] cookies = request.getCookies();
        //判断是否是用户名
        if(cookies != null){
            for(Cookie c : cookies){
                if(c.getName().equals("username")){
                    String username = c.getValue();//取出用户名
                    //保存到容器中
                    context.setVariable("username",username);
                }
                //判断是否是秘密吗
                if(c.getName().equals("password")){
                    String password = c.getValue();
                    context.setVariable("password",password);
                }
            }
        }
    }
}
