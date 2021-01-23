package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集和参数
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String intro = request.getParameter("intro");
        String categoryId = request.getParameter("categoryId");
        System.out.println(title);
        System.out.println(author);
        System.out.println(intro);
        System.out.println(categoryId);
        //获取上传文件
        Part filePart = request.getPart("file");
        String info = filePart.getHeader("content-disposition");
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        System.out.println(suffix);
        //得到唯一的文件名
        String fileName = UUID.randomUUID()+suffix;
        //得到日期相关的路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
        Date date = new Date();
        String datePath = format.format(date);
        System.out.println(datePath);
        //根据日期路径创建文件夹
        String path = request.getServletContext().getRealPath("images/"+datePath);
        new File(path).mkdirs();
        //检查target目录下是否出现2021文件夹
        filePart.write(path+fileName);
        //把参数封装到Product实体类中
        Product p = new Product(0,title,author,intro,"images/"+datePath+fileName,0,0,
                System.currentTimeMillis(),Integer.parseInt(categoryId));
        System.out.println(p);
        //创建Dao 并调用Insert方法
        ProductDao dao = new ProductDao();
        dao.insert(p);
        //重定向回到首页
        response.sendRedirect("/home");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
