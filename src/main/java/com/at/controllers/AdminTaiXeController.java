/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Taixe;
import com.at.pojo.Tuyenxe;
import com.at.service.TaiXeService;
import com.at.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thu
 */
@Controller
public class AdminTaiXeController {
    
    @Autowired
    TaiXeService  taiXeService ;
     @Autowired
    UserService userService; 
    
    
     @RequestMapping("/admin/taixe")
    public String showListtaixe( Model model,@RequestParam(value="kw", defaultValue = "" ) String kw,
          @RequestParam(value = "page" , required = false,defaultValue = "1" ) int page) {
        int activePage = page;

        List<Taixe> ListTX = taiXeService.listTX(kw);
        model.addAttribute("keyw",kw);
       model.addAttribute("onePage",7);
       model.addAttribute("ListTaiXe",taiXeService.getListTXPage(ListTX, activePage));
       model.addAttribute("countPage",ListTX.size());
        model.addAttribute("TaiXe", new Taixe());
        model.addAttribute("MaU",userService.getUserNull(2));
        return "adminTaiXe";
    }
    
    
    
    @PostMapping("/admin/taixe/add")
    public String addTX(@ModelAttribute(value = "TaiXe") Taixe tx) {

        if (taiXeService.addTX(tx)) {
            return "redirect:/admin/taixe";
        }
        
        return "index";
    }
    @PostMapping("/admin/taixe/update")
    public String updateTX(@ModelAttribute(value = "TaiXe") Taixe tx) {

        if (taiXeService.updateTX(tx)) {
            return "redirect:/admin/taixe";
        }
        return "index";
    }
    
}
