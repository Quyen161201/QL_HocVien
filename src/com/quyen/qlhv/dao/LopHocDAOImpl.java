/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import com.quyen.qlhv.model.Lophoc;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author Vutha
 */
public class LopHocDAOImpl implements LopHocDAO {
    public List<Lophoc> getLopHocList(){
        Connection conn=DBconnect.getConnection();
        String sql="select *from class_room";
        List<Lophoc> list = new ArrayList<>();
        try{PreparedStatement ps = conn.prepareCall(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Lophoc lophoc=new Lophoc();
                 
                 lophoc.setId(rs.getInt("id"));
                 lophoc.setName(rs.getString("name"));
                 lophoc.setKhoahoc(rs.getInt("course_id"));
                 lophoc.setStart_register(rs.getDate("date_registered"));
                 lophoc.setStatus(rs.getBoolean("status"));
                 
                 
               
                 list.add(lophoc);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
     public boolean delete(int id) {
        Connection conn = DBconnect.getConnection();
        String sql = "DELETE FROM class_room WHERE id = ?";
        boolean rowDeleted = false;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(LopHocDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return rowDeleted;
    }
     
    public boolean insert(Lophoc lophoc) {
    Connection conn = DBconnect.getConnection();
    boolean isSuccess = false; 

    String sql = "INSERT INTO class_room (id, name, course_id, date_registered, status) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
       
        ps.setInt(1, lophoc.getId());
        ps.setString(2, lophoc.getName());
        ps.setInt(3, lophoc.getKhoahoc());
        ps.setDate(4, new java.sql.Date(lophoc.getStart_register().getTime())); 
        ps.setBoolean(5, lophoc.isStatus());

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new class has been inserted successfully!");
            isSuccess = true; 
        }
    } catch (SQLException e) {
        Logger.getLogger(LopHocDAOImpl.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return isSuccess; 
}
    public boolean update(Lophoc lophoc) {
    Connection conn = DBconnect.getConnection();
    boolean isSuccess = false;

    String sql = "UPDATE class_room SET name = ?, course_id = ?, date_registered = ?, status = ? WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, lophoc.getName());
        ps.setInt(2, lophoc.getKhoahoc());
        ps.setDate(3, new java.sql.Date(lophoc.getStart_register().getTime()));
        ps.setBoolean(4, lophoc.isStatus());
        ps.setInt(5, lophoc.getId());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Class has been updated successfully!");
            isSuccess = true;
        }
    } catch (SQLException e) {
        Logger.getLogger(LopHocDAOImpl.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LopHocDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return isSuccess;
}


   
    
}
