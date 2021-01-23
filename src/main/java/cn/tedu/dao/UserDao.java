package cn.tedu.dao;

import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {
    public User login(String username, String password) {
        //获取连接
        try(Connection conn = DBUtils.getConn()){
            String sql = "select id from vrduser where username=? and password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                return new User(id,username,password);
            }
        } catch (Exception e) {
                    e.printStackTrace();
        }
        return null;//没查到
    }
}
