/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import java.util.List;
import com.quyen.qlhv.model.Hocvien;
/**
 *
 * @author MY PC
 */
public interface HocVienDAO {
    public List<Hocvien> getList();
    public int createOrUpdate(Hocvien hocVien);
    public int delete(Hocvien hocvien);

}