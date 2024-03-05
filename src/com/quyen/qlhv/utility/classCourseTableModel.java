/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.utility;

import com.quyen.qlhv.model.Khoahoc;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vutha
 */
public class classCourseTableModel {
   
   public DefaultTableModel setTableHocVien(List<Khoahoc> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: // ID
                        return Integer.class;
                    case 1: // STT
                        return Integer.class;
                    case 4: // Ngày bắt đầu
                        return Date.class;
                    case 5: // Ngày kết thúc
                        return Date.class;
                    case 6: // Trạng thái
                        return String.class; 
                    default:
                        return String.class;
                }
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        if (listItem != null) {
            for (Khoahoc khoahoc : listItem) {
                Object[] obj = new Object[listColumn.length];
                obj[0] = khoahoc.getId();
                obj[1] = dtm.getRowCount() + 1;
                obj[2] = khoahoc.getName();
                obj[3] = khoahoc.getDescription();
                obj[4] = khoahoc.getStart_date(); 
                obj[5] = khoahoc.getEnd_date(); 
                obj[6] = khoahoc.isStatus() ? "available" : "unavailable";
                dtm.addRow(obj);
            }
        }
        return dtm;
    }

    private String format(Date date) {
        if (date == null) {
            return ""; 
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
}
