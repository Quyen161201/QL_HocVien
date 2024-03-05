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
import com.quyen.qlhv.model.Khoahoc;

import java.text.ParseException;

/**
 *
 * @author Vutha
 */
public class editCourseFormController {

    private javax.swing.JTextField name;
    private javax.swing.JTextField startDate;
    private javax.swing.JRadioButton unavailable;
    private javax.swing.JTextArea description;
    private javax.swing.JTextField endDate;
    private javax.swing.JTextField ID;
    private javax.swing.JRadioButton available;

    public editCourseFormController(JTextField ID, JTextField name, JTextArea description, JTextField startDate, JTextField endDate, JRadioButton available, JRadioButton unavailable) {
        this.ID = ID;
        this.name = name;
        this.available = available;
        this.unavailable = unavailable;
        this.endDate = endDate;
        this.startDate = startDate;
        this.description = description;
    }

    public void reset() {
        ID.setText("");
        name.setText("");
        startDate.setText("");
        endDate.setText("");
        description.setText("");
        available.setSelected(false);
        unavailable.setSelected(false);
    }

    private boolean validateInput() {
        try {
            // Kiểm tra ID không được để trống và phải là số
            int courseId = Integer.parseInt(ID.getText());

            // Kiểm tra Name không được để trống và không quá 40 ký tự
            String courseName = name.getText();
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

    public void saveCourse() {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBconnect.getConnection();
            // Assuming the column names are 'name', 'description', 'start_date', 'end_date', and 'status' as per your database schema
            String sql = "UPDATE course SET name = ?, description = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);

            int courseId = Integer.parseInt(ID.getText());
            String courseName = name.getText();
            String courseDescription = description.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = dateFormat.parse(startDate.getText());
            Date end = dateFormat.parse(endDate.getText());
            boolean courseStatus = available.isSelected();

            int statusValue = courseStatus ? 1 : 0;

            pstmt.setString(1, courseName);
            pstmt.setString(2, courseDescription);
            pstmt.setDate(3, new java.sql.Date(start.getTime()));
            pstmt.setDate(4, new java.sql.Date(end.getTime()));
            pstmt.setInt(5, statusValue);
            pstmt.setInt(6, courseId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Course updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No existing course found to update or no change in data.");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating the course: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
