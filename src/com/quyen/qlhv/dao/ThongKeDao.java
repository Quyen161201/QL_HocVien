/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.dao;

import com.quyen.qlhv.bean.LopHocBean;
import java.util.List;

/**
 *
 * @author MY PC
 */
public interface ThongKeDao {
    public List<LopHocBean> getListByLopHoc();
}
