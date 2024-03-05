/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import com.quyen.qlhv.model.Khoahoc;
import com.quyen.qlhv.dao.KhoaHocDAO;
import java.util.ArrayList;
import java.util.List;
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
public class KhoahocDaoImpl implements KhoaHocDAO {
    public List<Khoahoc> getKhoahocList(){
        Connection conn=DBconnect.getConnection();
        String sql="select *from course";
        List<Khoahoc> list = new ArrayList<>();
        try{PreparedStatement ps = conn.prepareCall(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 Khoahoc khoahoc=new Khoahoc();
                 
                 khoahoc.setId(rs.getInt("id"));
                 khoahoc.setName(rs.getString("name"));
                 khoahoc.setDescription(rs.getString("description"));
                 khoahoc.setStart_date(rs.getDate("start_date"));
                 khoahoc.setEnd_date(rs.getDate("end_date"));
                 khoahoc.setStatus(rs.getBoolean("status"));
                 list.add(khoahoc);
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
        
    }
    
    public static void main(String[] args) {
        KhoaHocDAO khoahocDAO = new KhoahocDaoImpl();
        System.out.println("rs"+khoahocDAO.getKhoahocList());
    }
    
}
