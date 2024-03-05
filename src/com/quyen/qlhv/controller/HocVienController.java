/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;
import LIB.JPanelRound;
import com.quyen.qlhv.bean.ComboItemBean;
import com.quyen.qlhv.model.Hocvien;
import com.quyen.qlhv.service.HocVienService;
import com.quyen.qlhv.service.HocVienServiceImpl;
import com.quyen.qlhv.views.HocVienFrame;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import com.quyen.qlhv.model.Lophoc;
import com.quyen.qlhv.service.LophocServiceImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import com.quyen.qlhv.service.LophocService;

/**
 *
 * @author MY PC
 */
public class HocVienController {
    private JTextField jtfId;
    private JTextField jtfName;
    private JPanelRound btnSave;
    private JPanelRound btnDelete;
    private JTextField jtfPhone;
    private JTextField jtfEmail;
    private JRadioButton jRadioNam;
    private JRadioButton jRadioNu;
    private JTextArea jtaAddress;
    private JCheckBox jcbStatus;
    private JDateChooser jdcBirth;
    private JLabel jlbMess;
    private JComboBox jcbClass;
    private Hocvien hocvien = null;
    private HocVienService hocVienService = null;
    private LophocService lopHocService = null;

    public HocVienController(JTextField jtfId, JTextField jtfName, JPanelRound btnSave, JTextField jtfPhone, JTextField jtEmail, JRadioButton jRadioNam, JRadioButton jRadioNu, JTextArea jtaAddress, JCheckBox jcbStatus, JDateChooser jdcBirth,JLabel jlbMess,JPanelRound btnDelete,JComboBox jcbClass) {
        this.jtfId = jtfId;
        this.jtfName = jtfName;
        this.btnSave = btnSave;
        this.jtfPhone = jtfPhone;
        this.jtfEmail = jtEmail;
        this.jRadioNam = jRadioNam;
        this.jRadioNu = jRadioNu;
        this.jtaAddress = jtaAddress;
        this.jcbStatus = jcbStatus;
        this.jdcBirth = jdcBirth;
        this.jlbMess=jlbMess;
        this.hocVienService=new HocVienServiceImpl();
        this.lopHocService= new LophocServiceImpl();
        this.btnDelete = btnDelete;
        this.jcbClass = jcbClass;
    }

    
   public void setView(Hocvien hocvien){
         this.hocvien = hocvien;
         jtfId.setText(""+hocvien.getId());
         jtfName.setText(hocvien.getName());
         jtfPhone.setText(hocvien.getPhone());
         jtfEmail.setText(hocvien.getEmail());
          
         if(hocvien.isGender()){
             jRadioNam.setSelected(true);
             jRadioNu.setSelected(false);
         }
         else{
              jRadioNam.setSelected(false);
             jRadioNu.setSelected(true);
              
            }
         
        jtaAddress.setText(hocvien.getAddress());
        jcbStatus.setSelected(hocvien.isStatus());
        jdcBirth.setDate(hocvien.getDate_birth());
        List<Lophoc> listData = lopHocService.getLopHocList();
                
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                int record = listData.size();
                if(record > 0){
                    for(int i = 0; i< record;i++){
                        Lophoc lophoc = listData.get(i);
                        model.addElement(new ComboItemBean(lophoc.getId(),lophoc.getName()));
                       
                    }
                   
                }
                jcbClass.setModel(model);
                
     }
   
   public void setEvent(){
        hocvien = new Hocvien(); 
        btnSave.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(jtfName.getText().length()==0 || jdcBirth.getDate()==null){
                    jlbMess.setText("Phải nhập thông tin vào trường bắt buộc");
                }
                else{
                    if(showDialog()){
                    Object item=jcbClass.getSelectedItem();
                    
                    hocvien.setId(Integer.parseInt(jtfId.getText()));
                    hocvien.setName(jtfName.getText().trim());
                    hocvien.setGender(jRadioNam.isSelected());
                    hocvien.setPhone(jtfPhone.getText());
                    hocvien.setEmail(jtfEmail.getText());
                    hocvien.setDate_birth(jdcBirth.getDate());
                    hocvien.setAddress(jtaAddress.getText());
                    hocvien.setStatus(jcbStatus.isSelected());
                    hocvien.setClass_id(((ComboItemBean)item).getKey());
                    int lastId=hocVienService.createOrUpdate(hocvien);
                    if(lastId > 0){
                    hocvien.setId(lastId);
                    jtfId.setText(""+lastId);
                    jlbMess.setText("Cập nhật thông tin thành công");
                     }
                    }
                    else{
                        
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
                btnSave.setColorPrimario(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 btnSave.setColorPrimario(new Color(100,221,23));
            }
            
            private boolean showDialog() {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                return dialogResult == JOptionPane.YES_OPTION;
                }
            
        });
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
                    int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa", "Xóa dữ liệu", JOptionPane.YES_NO_OPTION);
                    // Kiểm tra xem người dùng đã nhấn nút "OK" chưa
                    if (check == JOptionPane.YES_NO_OPTION) {
                        hocvien.setId(Integer.parseInt(jtfId.getText()));
                        int result = hocVienService.delete(hocvien);
                        if(result > 0){
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                            ((JFrame) SwingUtilities.getWindowAncestor(btnDelete)).dispose();
                            
                        }
                         else{
                            jlbMess.setText("có lỗi xảy ra");
                        }

                }
               
            
    
            }
        
        
    });
        
       jcbClass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                Object item=jcbClass.getSelectedItem();
                System.out.println(".itemStateChanged()"+((ComboItemBean)item).getKey());
            }
       });
        
        
        
    }
    
}
