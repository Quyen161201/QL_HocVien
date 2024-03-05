/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.service;

import com.quyen.qlhv.model.Lophoc;
import java.util.List;

/**
 *
 * @author MY PC
 */
public interface LophocService {
      public boolean insert(Lophoc lophoc);
    public boolean update(Lophoc lophoc);
    public boolean delete(int i);
     public List<Lophoc> getLopHocList();
}
