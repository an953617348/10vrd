package cn.tedu.dao;

import cn.tedu.entity.Banner;
import cn.tedu.entity.Category;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BannerDao {
    public List<Banner> findAll() {
        ArrayList<Banner> list = new ArrayList<>();
        //获取连接
        try(Connection conn = DBUtils.getConn()){
            String sql = "select * from banner";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String url = rs.getString(2);
                list.add(new Banner(id,url));
            }
        } catch (Exception e) {
                    e.printStackTrace();
        }
        return list;
    }
}
