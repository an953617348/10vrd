package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rem =request.getParameter("rem");
        //创建UserDao 并调用login方法
        UserDao dao = new UserDao();
        User user =dao.login(username,password);
        if(user!=null){
            if(rem!=null){
                //创建cookie保存账号密码
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                //修改cookie保存时间
                c1.setMaxAge(60*60*24*30);
                //下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            //记住登录状态
            HttpSession session = request.getSession();
            //当前登录用户保存在session里面
            session.setAttribute("user",user);
            System.out.println("登陆成功!");
            //重定向到首页列表
            response.sendRedirect("/home");
        }else{
            System.out.println("登录失败!");
            //登录页面
            response.sendRedirect("/showlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
