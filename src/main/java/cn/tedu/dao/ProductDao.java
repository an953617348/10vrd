package cn.tedu.dao;

import cn.tedu.entity.Product;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void insert(Product p) {
        //获取连接
        try(Connection conn = DBUtils.getConn()){
            String sql = "insert into product values(null,?,?,?,?,0,0,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,p.getTitle());
            ps.setString(2,p.getAuthor());
            ps.setString(3,p.getIntro());
            ps.setString(4,p.getUrl());
            ps.setLong(5,p.getCreated());
            ps.setInt(6,p.getCategoryId());
            ps.executeUpdate();
            System.out.println("完成!");
        } catch (Exception e) {
                    e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try(Connection conn = DBUtils.getConn()){
            String sql = "select * from product";
            Statement s =conn.createStatement();
            ResultSet rs =s.executeQuery(sql);
            while(rs.next()){
                //取出数据,封装到PRoduct对象,并把对象添加到list集合
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id,title,author,intro,url,viewCount,likeCount,created,categoryId));
            }
        } catch (Exception e) {
                    e.printStackTrace();
        }
        return list;
    }

    public List<Product> findviewList() {
    }
}
