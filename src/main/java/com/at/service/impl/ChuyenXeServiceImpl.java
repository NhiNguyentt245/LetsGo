/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.service.impl;

import com.at.pojo.Chuyenxe;
import com.at.pojo.Hoadon;
import com.at.pojo.User;
import com.at.repository.ChuyenXeChoNgoiRepository;
import com.at.repository.ChuyenXeRepository;
import com.at.repository.HoaDonRepository;
import com.at.repository.impl.ChuyenXeRepositoryImpl;
import com.at.service.ChuyenXeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author thu
 */
@Service
public class ChuyenXeServiceImpl implements ChuyenXeService{

    @Autowired
    ChuyenXeRepository chuyenXeRepository;
    
     @Autowired
    ChuyenXeChoNgoiRepository chuyenXeChoNgoiRepository;
     
  
    @Override
    public List<Chuyenxe> listCX(String kw) {
        return this.chuyenXeRepository.listCX(kw);
    }

    @Override
    public boolean addChuyenXe(Chuyenxe cx) {
        
//       return chuyenXeChoNgoiRepository.addCXCN(49); 

        if( this.chuyenXeRepository.addChuyenXe(cx))
        {
           return true;
              
        }
        return false;
        
    }

    @Override
    public boolean deleteChuyenXe(int id) {

        return this.chuyenXeRepository.deleteCX(id);
               
       
    
    }

    @Override
    public Chuyenxe getChuyenXe(int id) {
        return this.chuyenXeRepository.getChuyenXe(id);
    }

    @Override
    public boolean updateCX(Chuyenxe cx) {
        return this.chuyenXeRepository.updateCX(cx);
    }

    @Override
    public List<Chuyenxe> getListCXPage(List<Chuyenxe> ListCX, int page) {
           int max = 7;
            int index = (page - 1) * max;
            List<Chuyenxe> cx = new ArrayList<>();
            for(int i =index; i< (max+index);i++)
            {
                if(i >= ListCX.size())
                    break;
                cx.add(ListCX.get(i));
            }
            return cx;

    }

    @Override
    public List<Chuyenxe> listCXSearch(String maTX, String day,int so) {
        return this.chuyenXeRepository.listCXSearch(maTX, day,so);
    }

    @Override
    public List<Chuyenxe> listCXSort(List<Chuyenxe> cx) {
        cx.sort((p1, p2) ->p1.getGiaVe().compareTo(p2.getGiaVe()));
        return cx;
        
    }

    @Override
    public BigDecimal getPrice(int macx) {
        return this.chuyenXeRepository.getPrice(macx);
        }

    @Override
    public List<Chuyenxe> getCXByTaiXe(User u) {
        return this.chuyenXeRepository.getCXByTaiXe(u);
    }
    
    

   
    
    
}
