/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import java.sql.Connection;
import com.quyen.qlhv.model.Hocvien;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MY PC
 */
public class HocvienDaoImpl implements HocVienDAO{

    @Override
    public List<Hocvien> getList() {
       Connection con = DBconnect.getConnection();
       String sql = "SELECT * FROM student";
       List<Hocvien> list = new ArrayList<>();
       
        try {
             PreparedStatement ps = con.prepareCall(sql);
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 Hocvien hocVien = new Hocvien();
                 hocVien.setId(rs.getInt("id"));
                 hocVien.setName(rs.getString("name"));
                 hocVien.setPhone(rs.getString("phone"));
                 hocVien.setEmail(rs.getString("email"));
                 hocVien.setAddress(rs.getString("address"));
                 hocVien.setDate_birth(rs.getDate("date_birth"));
                 hocVien.setGender(rs.getBoolean("gender"));
                 hocVien.setStatus(rs.getBoolean("status"));
                 list.add(hocVien);
             }
             ps.close();
             con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return list;
    }

    @Override
    public int createOrUpdate(Hocvien hocVien) {
        try {
            Connection con = DBconnect.getConnection();
            String sql = "INSERT INTO db_qlhv.student(id,name,gender,phone,email,date_birth,address,status)"
                    + " VALUES(?,?,?,?,?,?,?,?)ON DUPLICATE KEY UPDATE name=VALUES(name),gender=VALUES(gender),phone=VALUES(phone),"
                    + "email=VALUES(email),date_birth=VALUES(date_birth),address=VALUES(address),status=VALUES(status);";
             PreparedStatement ps = con.prepareCall(sql);
             ps.setInt(1,hocVien.getId());
             ps.setString(2,hocVien.getName());
             ps.setBoolean(3, hocVien.isGender());
             ps.setString(4, hocVien.getPhone());
             ps.setString(5, hocVien.getEmail());
             ps.setDate(6, new Date(hocVien.getDate_birth().getTime()) );
             ps.setString(7, hocVien.getAddress());
             ps.setBoolean(8, hocVien.isStatus());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            System.out.print("rs"+generatedKey);
            ps.close();
            con.close();
            
            return generatedKey;
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        return 0;
    }
    
    
}
