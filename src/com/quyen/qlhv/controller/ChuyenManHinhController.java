/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.controller;

import com.quyen.qlhv.bean.DanhMucBean;
import com.quyen.qlhv.views.HocVienJPanel;
import com.quyen.qlhv.views.KhoahocJpanel;
import com.quyen.qlhv.views.LophocJpanel;
import com.quyen.qlhv.views.ThongkeJpanel;
import com.quyen.qlhv.views.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MY PC
 */
public class ChuyenManHinhController {
    private JPanel root;
    private String kindSelected ="";
    List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected ="TrangChu";
        jpnItem.setBackground(new Color(96,100,191));
        jlbItem.setBackground(new Color(96,100,191));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }
    public void setEvent(List<DanhMucBean> listItem){
        
        for(DanhMucBean item : listItem){
            this.listItem=listItem;
            item.getJlb().addMouseListener(new LableEvent(item.getKind(),item.getJpn(),item.getJlb()));
        }
        
        
    }
    class LableEvent implements MouseListener{
        
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LableEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        

        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "HocVien":
                    node = new HocVienJPanel();
                    break;
                case "KhoaHoc":
                    node = new KhoahocJpanel();
                    break;
                    
                case "LopHoc":
                    node = new LophocJpanel();
                    break;
            
                case "ThongKe":
                    node = new ThongkeJpanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
            
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96,100,191));
            jlbItem.setBackground(new Color(96,100,191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            jpnItem.setBackground(new Color(76,175,80));
            jlbItem.setBackground(new Color(76,175,80));
        }
        
    }
    private void setChangeBackground(String kind){
        for(DanhMucBean item : listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                 item.getJpn().setBackground(new Color(96,100,191));
                 item.getJlb().setBackground(new Color(96,100,191));
            }
            else{
                 item.getJpn().setBackground(new Color(76,175,80));
                 item.getJlb().setBackground(new Color(76,175,80));
            }
        }
    }
}
