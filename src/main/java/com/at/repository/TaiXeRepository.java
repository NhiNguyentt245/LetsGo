/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.repository;

import com.at.pojo.Taixe;
import java.util.List;

/**
 *
 * @author thu
 */
public interface TaiXeRepository {
    List<Taixe> listTX(String kw);
    boolean deleTX(int id);
    boolean addTX(Taixe tx);
    Taixe getTaiXe(int id);
    boolean updateTX(Taixe tx);
}
