/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import com.quyen.qlhv.model.Hocvien;
import com.quyen.qlhv.model.Lophoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author MY PC
 */
public class LopHocDaoImpl implements LopHocDao{

    @Override
    public List<Lophoc> getList() {
        Connection con = DBconnect.getConnection();
       String sql = "SELECT * FROM class_room";
       List<Lophoc> list = new ArrayList<>();
       
        try {
             PreparedStatement ps = con.prepareCall(sql);
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 Lophoc lopHoc = new Lophoc();
                 lopHoc.setId(rs.getInt("id"));
                 lopHoc.setName(rs.getString("name"));
                 lopHoc.setCourse_id(rs.getInt("course_id"));
                 lopHoc.setStart_register(rs.getDate("date_resgister"));
                 lopHoc.setStatus(rs.getBoolean("status"));
                 list.add(lopHoc);
             }
             ps.close();
             con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return list;
    }
    
}
