/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Bill;
import com.at.pojo.Khachhang;
import com.at.service.KhachHangService;
import com.at.service.TuyenXeService;
import com.at.service.UserService;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author thu
 */
@Controller
@ControllerAdvice
public class HomeController {
    
    @Autowired
    KhachHangService khachHangService; 
    
    @Autowired
    UserService userService;
    
    @Autowired
    TuyenXeService tuyenXeService;
    
    
    @ModelAttribute
    public void commonAtt(Model model,HttpSession session)
    {
         int k = 0;
      Map<Integer,Bill> b = (Map<Integer,Bill>) session.getAttribute("bill");
        if(b != null)
            k = 1;
      model.addAttribute("flag",k);

    }
    
    @RequestMapping("/")
    public String home(Model model,HttpSession session)
    {
        Map<Integer, Set<String>> map = this.tuyenXeService.ListKhoiHanhDiemDen();            
        model.addAttribute("tenKH",khachHangService.getKH());
        model.addAttribute("xuatPhat",map.get(1));
        model.addAttribute("diemDen",map.get(2));
        model.addAttribute("ListTX",this.tuyenXeService.getListTuyenXe(null));
        return "home";
    }
    
   
  
    
    
   
    
    
}
