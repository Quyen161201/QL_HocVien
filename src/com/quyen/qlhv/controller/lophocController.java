/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.quyen.qlhv.dao.DBconnect;
import com.quyen.qlhv.model.Lophoc;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import com.quyen.qlhv.service.LophocService;
import com.quyen.qlhv.service.LophocServiceImpl;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.quyen.qlhv.utility.classroomTableModel;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.quyen.qlhv.views.particularClass;
import com.quyen.qlhv.views.editClassroom;
import com.quyen.qlhv.model.Hocvien;
import com.quyen.qlhv.dao.DBconnect;
import java.sql.ResultSet;
import com.quyen.qlhv.views.addClassForm;

/**
 *
 * @author Vutha
 */
public class lophocController {
    
    private javax.swing.JPanel jPanelView;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnDetail;
    
    private JTable table;
    private LophocService lophocService;
    private TableRowSorter<TableModel> rowSorter;
    
    private String[] listColumn = {"Stt", "Id", "Name", "Course Id", "Date registered", "Status"};
    
    public lophocController(JPanel jPanelView, JButton btnAdd, JButton btnDelete, JButton btnEdit, JButton btnRefresh, JButton btnDetail) {
        this.btnAdd = btnAdd;
        this.btnDelete = btnDelete;
        this.btnEdit = btnEdit;
        this.jPanelView = jPanelView;
        this.btnDetail = btnDetail;
        this.lophocService = new LophocServiceImpl();
        initTable();
        
    }
    
    public void setDataToTable() {
        List<Lophoc> listItem = lophocService.getLopHocList();
        if (listItem != null) {
            DefaultTableModel model = new classroomTableModel().setTableLopHoc(listItem, listColumn);
            table.setModel(model);
            rowSorter = new TableRowSorter<>(model);
            table.setRowSorter(rowSorter);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu khóa học.");
        }
    }
    
    private void initTable() {
        
        table = new JTable();
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(0, 50));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1150, 700));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Thiết lập renderer cho từng cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        jPanelView.setLayout(new BorderLayout());
        jPanelView.add(scrollPane, BorderLayout.CENTER);
        setDataToTable();
        
        if (table.getColumnCount() > 5) {
            table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                    if (column == 5 && value instanceof Boolean) {
                        boolean status = (Boolean) value;
                        setText(status ? "available" : "unavailable");
                    }
                    
                    return component;
                }
            });
        }
    }
    
    private static Date convertStringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private Lophoc getSelectedObjectFromRow(int selectedRow) {
        int id = Integer.parseInt(table.getValueAt(selectedRow, 1).toString());
        String name = table.getValueAt(selectedRow, 2).toString();
        int course_id = Integer.parseInt(table.getValueAt(selectedRow, 3).toString());
        Date date_registered = convertStringToDate(table.getValueAt(selectedRow, 4).toString());
        boolean status = false;
        if ("available".equals(table.getValueAt(selectedRow, 5).toString())) {
            status = true;
        }
        
        Lophoc lophoc = new Lophoc();
        lophoc.setId(id);
        lophoc.setKhoahoc(course_id);
        lophoc.setName(name);
        lophoc.setStart_register(date_registered);
        lophoc.setStatus(status);
        return lophoc;
        
    }
     public void showEditForm() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Lophoc selectedObject = getSelectedObjectFromRow(selectedRow);

            JDialog dialog = new JDialog();

            editClassroom editForm = new editClassroom();
            editForm.setData(selectedObject);

            dialog.setContentPane(editForm);
            dialog.setTitle("Edit form");
            dialog.setPreferredSize(new Dimension(700, 400));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
        } else {
           
            JOptionPane.showMessageDialog(null, "Bạn cần chọn một hàng từ bảng");
        }

    }
     public void deleteLopHoc() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow >= 0) {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa lớp học này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(table.getValueAt(selectedRow, 1).toString());
            boolean isDeleted = lophocService.delete(id);
            if (isDeleted) {
                JOptionPane.showMessageDialog(null, "Xóa lớp học thành công!");
                setDataToTable();  // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(null, "Xóa lớp học thất bại!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Bạn cần chọn một hàng từ bảng");
    }
}

    
    public void showDetail() {
        
        int selectedRow = table.getSelectedRow();
        
        if (selectedRow >= 0) {
            
            Lophoc lophoc = getSelectedObjectFromRow(selectedRow);
            int classId = lophoc.getId();
             
             
            particularClass x = new particularClass();
            x.displayStudentTable(classId);
        } else {
            
            JOptionPane.showMessageDialog(null, "Bạn cần chọn một hàng từ bảng");
        }
        
    }
    public void showAddform(){
     JDialog dialog = new JDialog();

            addClassForm addForm = new addClassForm();
            

            dialog.setContentPane(addForm);
            dialog.setTitle("Add form");
            dialog.setPreferredSize(new Dimension(700, 400));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);}
    
}
