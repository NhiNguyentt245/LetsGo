/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.repository;

import com.at.pojo.Nhanvien;
import java.util.List;

/**
 *
 * @author thu
 */
public interface NhanVienRepository {
    List<Nhanvien> getListNV(String kw);
    List<Nhanvien> getListNVPage(List<Nhanvien> ListNV, int page);
    boolean addNV(Nhanvien nv);
    boolean deleteNV(int id);
    boolean updateNV(Nhanvien nv);
    Nhanvien getNV(int id);
    String getUserNV(int id);
    
    
}
