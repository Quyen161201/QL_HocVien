/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.utility;

import com.quyen.qlhv.model.Lophoc;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Vutha
 */
public class classroomTableModel {

    public DefaultTableModel setTableLopHoc(List<Lophoc> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: // cột 'id'
                        return Integer.class;
                    case 1: // cột 'name'
                        return String.class;
                    case 2: // cột 'course_id'
                        return Integer.class;
                    case 3: // cột 'date_registered'
                        return String.class;
                    case 4: // cột 'status'
                        return String.class;
                    default:
                        return String.class;
                }
            }

        };
        dtm.setColumnIdentifiers(listColumn);
        int column = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                Lophoc lophoc = listItem.get(i);
                obj = new Object[column];
                obj[0] = i + 1;
                obj[1] = lophoc.getId();
                obj[2] = lophoc.getName();
                obj[3] = lophoc.getKhoahoc();
                obj[4] = format(lophoc.getStart_register());
                obj[5] = convertString(lophoc.isStatus()) ;
                dtm.addRow(obj);
            }
        }

        return dtm;
    }

    private static String format(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }

    private static String convertString(boolean flag) {

        return flag == true ? "available" : "unavailable";
    }

}
