/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.repository;

import com.at.pojo.Tuyenxe;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author thu
 */
public interface TuyenXeRepository {
    List<Tuyenxe> getListTuyenXe(String kw);
    boolean addTuyenXe(Tuyenxe tx);
    boolean updateTuyenXe(Tuyenxe tx);
    Tuyenxe getTuyenXe(String id);
    boolean deleteTuyenXe(String id);
    Map<Integer,Set<String>> ListKhoiHanhDiemDen();
    
}
