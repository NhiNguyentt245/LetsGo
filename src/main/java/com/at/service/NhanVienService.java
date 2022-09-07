/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.service;

import com.at.pojo.Nhanvien;
import java.util.List;

/**
 *
 * @author thu
 */
public interface NhanVienService {
    public List<Nhanvien> getListNV(String kw);
    List<Nhanvien> getListNVPage(List<Nhanvien> ListNV, int page);
     public boolean addNV(Nhanvien nv);
    public boolean deleteNV(int id);
    boolean updateNV(Nhanvien nv);
    Nhanvien getNV(int id);
    String getUserNV(int id);

   
}
