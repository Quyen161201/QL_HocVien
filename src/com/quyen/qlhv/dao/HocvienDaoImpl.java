/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import java.sql.Connection;
import com.quyen.qlhv.model.Hocvien;
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
    public static void main(String[] args) {
        HocVienDAO hocVienDAO = new HocvienDaoImpl();
        System.out.println("rs"+hocVienDAO.getList());
    }
    
}
