/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.dao.LopHocDao;
import com.quyen.qlhv.dao.LopHocDaoImpl;
import com.quyen.qlhv.model.Lophoc;
import java.util.List;

/**
 *
 * @author MY PC
 */
public class LophocServiceImpl implements LopHocService{
    private LopHocDao LopHocDao = null;

    public LophocServiceImpl() {
        this.LopHocDao = new LopHocDaoImpl();
    }
    
    @Override
    public List<Lophoc> getList() {
        return LopHocDao.getList();
    }
    
}
