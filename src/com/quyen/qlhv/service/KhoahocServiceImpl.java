/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.model.Khoahoc;
import java.util.List;
import com.quyen.qlhv.dao.KhoaHocDAO;
import com.quyen.qlhv.dao.KhoahocDaoImpl;
import com.quyen.qlhv.service.KhoahocService;

/**
 *
 * @author Vutha
 */
public class KhoahocServiceImpl implements KhoahocService {
    private KhoaHocDAO khoahocDAO=null;
    public KhoahocServiceImpl (){
        this.khoahocDAO=new KhoahocDaoImpl();
    }
    @Override
    public List<Khoahoc> getKhoaHocList(){
        
    return khoahocDAO.getKhoahocList();
    }
    
}
