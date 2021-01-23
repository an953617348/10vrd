package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.entity.Product;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context context = new Context();
        /*ThUtils.print("home.html",context,response);*/
        CategoryDao dao = new CategoryDao();
        List<Category> list = dao.findall();
        context.setVariable("list",list);
        BannerDao dao1 = new BannerDao();
        List<Banner> list1 = dao1.findAll();
        context.setVariable("list1",list1);
        //获取Session对象
        HttpSession session = request.getSession();
        //取出
        User user = (User)session.getAttribute("user");
        /*if (user==null){
            System.out.println("没登录过");
        }else{
            System.out.println("已经登陆过");
        }*/
        //把用户对象装进容器
        context.setVariable("user",user);
        //查询出所有作品装进context容器
        ProductDao pDao = new ProductDao();
        List<Product> pList = pDao.findAll();
        context.setVariable("pList",pList);
        List<Product> vList = pDao.findviewList();
        context.setVariable("vList",vList);
        ThUtils.print("home.html",context,response);


    }
}
