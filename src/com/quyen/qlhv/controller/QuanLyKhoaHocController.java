/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;

/**
 *
 * @author Vutha
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import com.quyen.qlhv.views.addCourseForm;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import com.quyen.qlhv.service.KhoahocService;
import com.quyen.qlhv.service.KhoahocServiceImpl;
import java.util.List;
import com.quyen.qlhv.model.Khoahoc;
import com.quyen.qlhv.utility.classCourseTableModel;
import javax.swing.JOptionPane;
import java.awt.Component;
import com.quyen.qlhv.views.editCourseForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.quyen.qlhv.dao.DBconnect;

/**
 *
 * /**
 *
 * @author Vutha
 */
public class QuanLyKhoaHocController {

    private JPanel jpnView;
    private JButton btnDelete, btnEdit, btnAdd;
    private JTextField jtfSearch;
    private KhoahocService khoaHocService;
    private JTable table;
    private TableRowSorter<TableModel> rowSorter;
    private String[] listColumn = {"ID", "STT", "Tên khóa học", "Mô tả", "Ngày bắt đầu", "Ngày kết thúc", " Trạng thái"};

    public QuanLyKhoaHocController(JPanel jpnView, JButton btnDelete, JButton btnEdit, JButton btnAdd, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnDelete = btnDelete;
        this.btnEdit = btnEdit;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.khoaHocService = new KhoahocServiceImpl();

        initTable();
    }

    private void initTable() {
        table = new JTable();
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1150, 700));

        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane, BorderLayout.CENTER);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            private void search(String str) {
                if (str.length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });

        setDataToTable();
        if (table.getColumnCount() > 6) {
            table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (column == 6 && value instanceof Boolean) {
                        boolean status = (Boolean) value;
                        setText(status ? "available" : "unavailable");
                    }

                    return component;
                }
            });
        }
    }

    public void showAddCourseForm() {
        JDialog dialog = new JDialog(); // Sử dụng JDialog để hiển thị form con
        addCourseForm addForm = new addCourseForm(); // Tạo instance của form con
        dialog.setContentPane(addForm); // Đặt addCourseForm làm nội dung của JDialog
        dialog.setTitle("Add courser"); // Đặt tiêu đề cho JDialog
        dialog.setPreferredSize(new Dimension(700, 400));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    public void setDataToTable() {
        List<Khoahoc> listItem = khoaHocService.getKhoaHocList();
        if (listItem != null) {
            DefaultTableModel model = new classCourseTableModel().setTableHocVien(listItem, listColumn);
            table.setModel(model);
            rowSorter = new TableRowSorter<>(model);
            table.setRowSorter(rowSorter);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tải dữ liệu khóa học.");
        }
    }

    public void showEditCourseForm() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Khoahoc selectedObject = getSelectedObjectFromRow(selectedRow);

            JDialog dialog = new JDialog();

            editCourseForm editForm = new editCourseForm();
            editForm.setData(selectedObject);

            dialog.setContentPane(editForm);
            dialog.setTitle("Edit form");
            dialog.setPreferredSize(new Dimension(700, 400));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setVisible(true);
        } else {
            // Hiển thị thông báo nếu không có hàng nào được chọn
            JOptionPane.showMessageDialog(null, "Bạn cần chọn một hàng từ bảng");
        }

    }

    private Khoahoc getSelectedObjectFromRow(int selectedRow) {
        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        String name = (String) table.getValueAt(selectedRow, 2);
        String description = (String) table.getValueAt(selectedRow, 3);

        Date startDate = (Date) table.getValueAt(selectedRow, 4);
        Date endDate = (Date) table.getValueAt(selectedRow, 5);

        boolean status = false; 
        if ("available".equals(table.getValueAt(selectedRow, 6).toString())) {
            status = true;
        }

        Khoahoc khoahoc = new Khoahoc();
        khoahoc.setId(id);
        khoahoc.setName(name);
        khoahoc.setDescription(description);
        khoahoc.setStart_date(startDate); // Đảm bảo tên phương thức đúng với định nghĩa trong lớp Khoahoc
        khoahoc.setEnd_date(endDate); // Đảm bảo tên phương thức đúng với định nghĩa trong lớp Khoahoc
        khoahoc.setStatus(status);

        return khoahoc;
    }
    public void deleteCourse() {
        int selectedRow = table.getSelectedRow();
        Khoahoc khoahoc=getSelectedObjectFromRow(selectedRow);
    if (khoahoc == null || khoahoc.getId() <=0) {
        JOptionPane.showMessageDialog(null, "No course selected or course has no ID!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course?", "Delete", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DBconnect.getConnection();
        String sql = "DELETE FROM course WHERE id = ?";
        pstmt = conn.prepareStatement(sql);

        // Đặt tham số ID cho prepared statement
        pstmt.setInt(1, khoahoc.getId());

        // Thực thi truy vấn
        int affectedRows = pstmt.executeUpdate();

        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "Course deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "No course found with provided ID, or course could not be deleted.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error deleting the course: " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}


}
