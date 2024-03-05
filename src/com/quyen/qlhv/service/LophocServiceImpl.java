/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.model.Lophoc;
import com.quyen.qlhv.dao.LopHocDAOImpl;
import java.util.List;

/**
 *
 * @author Vutha
 */
public class LophocServiceImpl implements LophocService {

    private LopHocDAOImpl lophocDAOImpl = null;

    public LophocServiceImpl() {
        this.lophocDAOImpl = new LopHocDAOImpl();
    }

    public List<Lophoc> getLopHocList() {
        return lophocDAOImpl.getLopHocList();
    }

    public boolean insert(Lophoc lophoc) {
        return lophocDAOImpl.insert(lophoc);
    }

    ;
    public boolean update(Lophoc lophoc) {
        return lophocDAOImpl.update(lophoc);
    }

    ;
    public boolean delete(int i) {
        return lophocDAOImpl.delete(i);
    }
;

}
