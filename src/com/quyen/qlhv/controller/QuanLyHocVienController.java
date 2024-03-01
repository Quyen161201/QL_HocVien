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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import LIB.JPanelRound;
import java.awt.CardLayout;
/**
 *
 * @author MY PC
 */
public class QuanLyHocVienController {
    private JPanel jpnView;
    private JPanelRound btnCreate;
    private JTextField jtfSearch;
    private HocVienService hocVienService = null;
    private String [] listColumn = {"Mã học viên","STT","Tên học viên","Ngày sinh","Giới tính","Số điện thoại","Email","Địa chỉ","Trạng thái"};
    private  TableRowSorter<TableModel> rowSorter = null;
    private ClassTableModel classTableModel= null;
    public QuanLyHocVienController(JPanel jpnView, JPanelRound btnCreate, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnCreate = btnCreate;
        this.jtfSearch = jtfSearch;
        this.hocVienService = new HocVienServiceImpl();
        this.classTableModel = new ClassTableModel();
        
    }
    
    public void setDataToTable(){
        List<Hocvien> listItem = hocVienService.getList();
        DefaultTableModel model = new ClassTableModel().setTableHocVien(listItem,listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                    
                }
                else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length()==0){
                    rowSorter.setRowFilter(null);
                    
                }
                else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
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


        
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
        table.getTableHeader().setPreferredSize(new Dimension(100,50));
        table.validate();
        table.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1150,700));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
       
        jpnView.validate();
        jpnView.repaint();
      
        
    }
    
    
    
}
