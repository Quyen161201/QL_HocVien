/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.quyen.qlhv.views;

import com.quyen.qlhv.controller.QuanLyHocVienController;

/**
 *
 * @author MY PC
 */
public class HocVienJPanel extends javax.swing.JPanel {

    
    public HocVienJPanel() {
        initComponents();
        QuanLyHocVienController controller = new QuanLyHocVienController(jpnView, btnCreate, jtfSearch,btnCreate,btnExcel);
        controller.setDataToTable();
        controller.setEvent();
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jtfSearch = new LIB.JTexfieldPH_FielTex();
        btnCreate = new LIB.JPanelRound();
        jlb_create = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();
        btnExcel = new LIB.JPanelRound();
        jlbExcel = new javax.swing.JLabel();

        jpnRoot.setBackground(new java.awt.Color(204, 204, 204));

        jtfSearch.setBorder(null);
        jtfSearch.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jtfSearch.setPlaceholder("Tìm kiếm...");

        btnCreate.setBackground(new java.awt.Color(52, 189, 235));
        btnCreate.setColorPrimario(new java.awt.Color(52, 189, 235));
        btnCreate.setColorSecundario(new java.awt.Color(52, 189, 235));

        jlb_create.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlb_create.setForeground(new java.awt.Color(255, 255, 255));
        jlb_create.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_create.setText("+ Thêm mới");

        javax.swing.GroupLayout btnCreateLayout = new javax.swing.GroupLayout(btnCreate);
        btnCreate.setLayout(btnCreateLayout);
        btnCreateLayout.setHorizontalGroup(
            btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCreateLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jlb_create)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        btnCreateLayout.setVerticalGroup(
            btnCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCreateLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(jlb_create, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        btnExcel.setBackground(new java.awt.Color(0, 255, 51));
        btnExcel.setColorPrimario(new java.awt.Color(51, 255, 51));
        btnExcel.setColorSecundario(new java.awt.Color(0, 255, 102));

        jlbExcel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlbExcel.setForeground(new java.awt.Color(255, 255, 255));
        jlbExcel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbExcel.setText("Xuất Excel");

        javax.swing.GroupLayout btnExcelLayout = new javax.swing.GroupLayout(btnExcel);
        btnExcel.setLayout(btnExcelLayout);
        btnExcelLayout.setHorizontalGroup(
            btnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExcelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jlbExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        btnExcelLayout.setVerticalGroup(
            btnExcelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExcelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 394, Short.MAX_VALUE)
                .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.JPanelRound btnCreate;
    private LIB.JPanelRound btnExcel;
    private javax.swing.JLabel jlbExcel;
    private javax.swing.JLabel jlb_create;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnView;
    private LIB.JTexfieldPH_FielTex jtfSearch;
    // End of variables declaration//GEN-END:variables
}
