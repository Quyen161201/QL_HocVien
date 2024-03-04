/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.bean.LopHocBean;
import com.quyen.qlhv.dao.ThongKeDao;
import com.quyen.qlhv.dao.ThongKeDaoImpl;
import java.util.List;

/**
 *
 * @author MY PC
 */
public class ThongKeServiceImpl implements ThongKeService
{
    private ThongKeDao thongKeDAO = null;

    public ThongKeServiceImpl() {
        this.thongKeDAO = new ThongKeDaoImpl();
    }

    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDAO.getListByLopHoc();
    }
    
}
