/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.dao.LopHocDaoImpl;
import com.quyen.qlhv.dao.LopHocDaoImpl;
import com.quyen.qlhv.model.Lophoc;
import java.util.List;

/**
 *
 * @author MY PC
 */
public class LophocServiceImpl implements LophocService{
    private LopHocDaoImpl lophocDAOImpl = null;
    
    public LophocServiceImpl() {
        this.lophocDAOImpl = new LopHocDaoImpl();
    }
    @Override
    public boolean insert(Lophoc lophoc) {
        return lophocDAOImpl.insert(lophoc);
    }

    @Override
    public boolean update(Lophoc lophoc) {
         return lophocDAOImpl.update(lophoc);
    }

    @Override
    public boolean delete(int i) {
        return lophocDAOImpl.delete(i);
    }

    @Override
    public List<Lophoc> getLopHocList() {
         return lophocDAOImpl.getLopHocList();
    }
    
}
