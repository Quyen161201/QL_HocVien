/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import com.quyen.qlhv.bean.LopHocBean;
import java.sql.Connection;
import com.quyen.qlhv.model.Hocvien;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MY PC
 */
public class ThongKeDaoImpl implements ThongKeDao{

    @Override
    public List<LopHocBean> getListByLopHoc() {
        Connection con = DBconnect.getConnection();
        String sql = "select cr.name, count(*) as 'student_count'  from db_qlhv.student s, db_qlhv.class_room cr where s.class_id =cr.id group by cr.id";
        List<LopHocBean> list = new ArrayList<>();
         try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setName(rs.getString("name"));
                lopHocBean.setStudent_count(rs.getInt("student_count"));
                list.add(lopHocBean);
            }
            ps.close();
            con.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
}
