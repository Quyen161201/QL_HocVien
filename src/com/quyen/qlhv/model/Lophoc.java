/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.model;

import java.util.Date;

/**
 *
 * @author MY PC
 */
public class Lophoc {

    private int id;
    private int Khoahoc;
    private Hocvien Hocvien;
    private Date start_register;
    private boolean status;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKhoahoc() {
        return this.Khoahoc;
    }

    public void setKhoahoc(int khoahoc) {
        this.Khoahoc = khoahoc;
    }

    public Hocvien getHocvien() {
        return Hocvien;
    }

    public void setHocvien(Hocvien Hocvien) {
        this.Hocvien = Hocvien;
    }

    public Date getStart_register() {
        return start_register;
    }

    public void setStart_register(Date start_register) {
        this.start_register = start_register;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}