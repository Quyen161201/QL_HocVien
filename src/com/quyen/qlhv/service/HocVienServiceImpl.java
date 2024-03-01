/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.model.Hocvien;
import java.util.List;
import com.quyen.qlhv.dao.HocVienDAO;
import com.quyen.qlhv.dao.HocvienDaoImpl;

/**
 *
 * @author MY PC
 */
public class HocVienServiceImpl implements HocVienService{
    private HocVienDAO HocVienDao = null;
    public HocVienServiceImpl (){
        this.HocVienDao = new HocvienDaoImpl();
    }
    @Override
    public List<Hocvien> getList() {
       return HocVienDao.getList();
    }
    
}
