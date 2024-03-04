/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quyen.qlhv.bean;

/**
 *
 * @author MY PC
 */
public class ComboItemBean {
    private int key;
    private String value;

    public ComboItemBean(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return  value;
    }

    
    
    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
    
}
