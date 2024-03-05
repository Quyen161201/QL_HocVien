/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;

/**
 *
 * @author Vutha
 */
import com.quyen.qlhv.dao.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.text.ParseException;


/**
 *
 * @author Vutha
 */
public class addCourseController {

    private javax.swing.JTextField ID;
    private javax.swing.JTextField Name;

    private javax.swing.JRadioButton available;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextArea description;
    private javax.swing.JTextField endDate;

    private javax.swing.JTextField startDate;
    private javax.swing.JRadioButton unavailable;
    
    public addCourseController(JTextField ID, JTextField Name, JTextArea description, JTextField startDate, JTextField endDate, JRadioButton available, JRadioButton unavailable) {
    this.ID = ID;
    this.Name = Name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.available = available;
    this.unavailable = unavailable;
}
    public void reset() {
        ID.setText("");
        Name.setText("");
        startDate.setText("");
        endDate.setText("");
        description.setText("");
        available.setSelected(false);
        unavailable.setSelected(false);
    }
    
    public boolean addCourse() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
     
        conn = DBconnect.getConnection();
        
        String sql = "INSERT INTO course (id, name, description, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, Integer.parseInt(ID.getText())); 
        pstmt.setString(2, Name.getText());
        pstmt.setString(3, description.getText()); // 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        pstmt.setDate(4, new java.sql.Date(dateFormat.parse(startDate.getText()).getTime())); // Giả định trường startDate chứa ngày bắt đầu
        pstmt.setDate(5, new java.sql.Date(dateFormat.parse(endDate.getText()).getTime())); // Giả định trường endDate chứa ngày kết thúc
        pstmt.setBoolean(6, available.isSelected()); // Giả định available là một nút radio cho trạng thái của khóa học
        
       
        int result = pstmt.executeUpdate();
        
       
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Khóa học đã được thêm thành công!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Không thể thêm khóa học!");
            return false;
        }
        
    } catch (SQLException | ParseException | NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        e.printStackTrace();
        return false;
    } finally {
       
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi đóng kết nối: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


    
private boolean validateInput() {
    try {
        // Kiểm tra ID không được để trống và phải là số
        int courseId = Integer.parseInt(ID.getText());
        
        // Kiểm tra Name không được để trống và không quá 40 ký tự
        String courseName = Name.getText();
        if (courseName.isEmpty() || courseName.length() > 40) {
            return false;
        }

        // Kiểm tra Description không được để trống
        String courseDescription = description.getText();
        if (courseDescription.isEmpty()) {
            return false;
        }

       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date start = dateFormat.parse(startDate.getText());
        Date end = dateFormat.parse(endDate.getText());

       
        if (available.isSelected() == unavailable.isSelected()) {
            return false;
        }

    } catch (NumberFormatException | ParseException e) {
        
        e.printStackTrace();
        return false;
    }

 
    return true;
}

   


}
