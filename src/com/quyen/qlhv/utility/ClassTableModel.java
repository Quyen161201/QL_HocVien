/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.utility;
import com.quyen.qlhv.model.Hocvien;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MY PC
 */
public class ClassTableModel {
    public DefaultTableModel setTableHocVien(List<Hocvien> listItem, String[] listColumn){
        DefaultTableModel dtm= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 8 ? Boolean.class : String.class;
            }
            
            
        };
        dtm.setColumnIdentifiers(listColumn);
        int column = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0){
            for(int i=0;i<rows;i++){
                Hocvien hocvien = listItem.get(i);
                obj = new Object[column];
                obj[0]=hocvien.getId();
                obj[1]=i+1;
                obj[2]=hocvien.getName();
                obj[3]=hocvien.getDate_birth();
                obj[4]=hocvien.isGender()==true?"Nam":"Nữ";
                obj[5]=hocvien.getPhone();
                obj[6]=hocvien.getEmail();
                obj[7]=hocvien.getAddress();
                obj[8]=hocvien.isStatus();
                dtm.addRow(obj);
            }
        }
        return dtm;
        
    }
}
