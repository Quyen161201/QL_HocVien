/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import com.quyen.qlhv.service.HocVienService;
import com.quyen.qlhv.service.HocVienServiceImpl;
import java.util.List;
import com.quyen.qlhv.model.Hocvien;
import com.quyen.qlhv.utility.ClassTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import LIB.JPanelRound;
import com.quyen.qlhv.views.HocVienFrame;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author MY PC
 */
public class QuanLyHocVienController {

    private JPanel jpnView;
    private JPanelRound btnCreate;
    private JPanelRound btnDelete;
    private JTextField jtfSearch;
    private JPanelRound btnExcel;
    private HocVienService hocVienService = null;
    private String[] listColumn = {"Mã học viên", "STT", "Tên học viên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Địa chỉ", "Trạng thái"};
    private TableRowSorter<TableModel> rowSorter = null;
    private ClassTableModel classTableModel = null;

    public QuanLyHocVienController(JPanel jpnView, JPanelRound btnCreate, JTextField jtfSearch, JPanelRound btnDelete, JPanelRound btnExcel) {
        this.jpnView = jpnView;
        this.btnCreate = btnCreate;
        this.jtfSearch = jtfSearch;
        this.btnDelete = btnDelete;
        this.btnExcel = btnExcel;
        this.hocVienService = new HocVienServiceImpl();
        this.classTableModel = new ClassTableModel();

    }

    public void setDataToTable() {
        List<Hocvien> listItem = hocVienService.getList();
        DefaultTableModel model = new ClassTableModel().setTableHocVien(listItem, listColumn);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);

                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);

                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);

        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    int selectedRowindex = table.getSelectedRow();
                    selectedRowindex = table.convertRowIndexToModel(selectedRowindex);

                    Hocvien hocVien = new Hocvien();
                    hocVien.setId((int) model.getValueAt(selectedRowindex, 0));
                    hocVien.setName(model.getValueAt(selectedRowindex, 2).toString());
                    hocVien.setGender(model.getValueAt(selectedRowindex, 4).toString().equalsIgnoreCase("Nam"));
                    hocVien.setDate_birth((Date) model.getValueAt(selectedRowindex, 3));
                    hocVien.setPhone(model.getValueAt(selectedRowindex, 5) != null ? model.getValueAt(selectedRowindex, 5).toString() : "");
                    hocVien.setEmail(model.getValueAt(selectedRowindex, 6) != null ? model.getValueAt(selectedRowindex, 6).toString() : "");
                    hocVien.setAddress(model.getValueAt(selectedRowindex, 7) != null ? model.getValueAt(selectedRowindex, 7).toString() : "");
                    hocVien.setStatus((boolean) model.getValueAt(selectedRowindex, 8));

                    HocVienFrame frame = new HocVienFrame(hocVien);

                    frame.setTitle("Thông tin học viên");
                    frame.setTextField();
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);

                    frame.setVisible(true);
                }

            }

        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.validate();
        table.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1150, 700));

        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);

        jpnView.validate();
        jpnView.repaint();

    }

    public void setEvent() {
        btnCreate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HocVienFrame frame = new HocVienFrame(new Hocvien());
                frame.setBtn();
                frame.setVisible(true);
                frame.setTitle("Thông tin học viên");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

            }

            @Override
            public void mouseEntered(MouseEvent e) {

                btnCreate.setColorPrimario(new Color(0, 200, 83));
                btnCreate.setColorSecundario(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnCreate.setColorPrimario(new Color(0, 200, 83));
                btnCreate.setColorSecundario(new Color(0, 200, 83));
            }

        });

        btnExcel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    XSSFWorkbook workBook = new XSSFWorkbook();
                    XSSFSheet sheet = workBook.createSheet("Danh sách học Viên");

                    XSSFRow row = null;
                    Cell cell = null;
                    row = sheet.createRow(3);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue("STT");
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue("Họ và tên");
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue("Ngày sinh");
                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue("Giới tính");
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue("Số điện thoại");
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue("Địa chỉ");

                   
                    sheet.setColumnWidth(0, 2000);
                    sheet.setColumnWidth(1, 7000);
                    sheet.setColumnWidth(2, 4000);
                    sheet.setColumnWidth(4, 7000);
                    sheet.setColumnWidth(5, 8000);

                    HocVienService hocVienService = new HocVienServiceImpl();

                    List<Hocvien> listItem = hocVienService.getList();

                    for (int i = 0; i < listItem.size(); i++) {
                        Hocvien hocVien = listItem.get(i);
                        row = sheet.createRow((short) 4 + i);
                        row.setHeight((short) 400);
                        row.createCell(0).setCellValue(i + 1);
                        row.createCell(1).setCellValue(hocVien.getName());
                        row.createCell(2).setCellValue(hocVien.getDate_birth().toString());
                        row.createCell(3).setCellValue(hocVien.isGender() ? "Nam" : "Nữ");
                        row.createCell(4).setCellValue(hocVien.getPhone());
                        row.createCell(5).setCellValue(hocVien.getAddress());
                    }

                    FileOutputStream out = new FileOutputStream(new File("E:/hv.xlsx"));
                    workBook.write(out);
                    out.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }
}
