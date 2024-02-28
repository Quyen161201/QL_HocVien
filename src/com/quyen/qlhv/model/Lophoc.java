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
    private int ma_lop_hoc;
    private Khoahoc khoahoc;
    private Hocvien hocvien;
    private Date ngay_dang_ky;
    private boolean tinh_trang;

    public int getMa_lop_hoc() {
        return ma_lop_hoc;
    }

    public void setMa_lop_hoc(int ma_lop_hoc) {
        this.ma_lop_hoc = ma_lop_hoc;
    }

    public Khoahoc getKhoahoc() {
        return khoahoc;
    }

    public void setKhoahoc(Khoahoc khoahoc) {
        this.khoahoc = khoahoc;
    }

    public Hocvien getHocvien() {
        return hocvien;
    }

    public void setHocvien(Hocvien hocvien) {
        this.hocvien = hocvien;
    }

    public Date getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(Date ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
    }

    public boolean isTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
    
    
}
