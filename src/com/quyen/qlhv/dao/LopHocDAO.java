/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.quyen.qlhv.dao;
import com.quyen.qlhv.model.Lophoc;
import java.util.List;

/**
 *
 * @author Vutha
 */
public interface LopHocDAO {
     public List<Lophoc> getLopHocList();
    public boolean insert(Lophoc lophoc);
    public boolean update(Lophoc lophoc);
    public boolean delete(int i);
}
