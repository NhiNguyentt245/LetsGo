/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Chuyenxe;
import com.at.pojo.Nhanvien;
import com.at.pojo.Tuyenxe;
import com.at.service.TuyenXeService;
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
public class AdminTuyenXeController {
    @Autowired
    TuyenXeService tuyenXeService;
    
    
     @RequestMapping("/admin/tuyenxe")
    public String showListtuyenxe( Model model,@RequestParam(value="kw", defaultValue = "" ) String kw,
            @RequestParam(value = "page" , required = false,defaultValue = "1" ) int page) {

        
        int activePage = page;
       
        List<Tuyenxe> ListTX = tuyenXeService.getListTuyenXe(kw);
        model.addAttribute("keyw",kw);
       model.addAttribute("onePage",7);
       model.addAttribute("ListTuyenXe",tuyenXeService.getListTXPage(ListTX, page));
       model.addAttribute("countPage",ListTX.size());
       
       model.addAttribute("TuyenXe", new Tuyenxe());

        return "adminTuyenXe";
    }
    
    @PostMapping("/admin/tuyenxe/add")
    public String addtuyenxe( @ModelAttribute(value = "TuyenXe") Tuyenxe tx) {
	
        if(this.tuyenXeService.addTuyenXe(tx))
        {
            return "redirect:/admin/tuyenxe";
        }

        return "index";
    }
    
     @PostMapping("/admin/tuyenxe/update")
    public String updateTX(@ModelAttribute(value = "Nhanvien") Tuyenxe tx) {
        
        if (tuyenXeService.updateTuyenXe(tx)) {
            return "redirect:/admin/tuyenxe";
        }
        return "/";
    }
}
