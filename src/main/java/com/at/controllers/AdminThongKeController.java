/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Tuyenxe;
import com.at.service.BieuDoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thu
 */
@Controller
 @RequestMapping("/admin/thongke")
public class AdminThongKeController {
    
    @Autowired
    BieuDoService bieuDoService;
    
     @GetMapping("/doanhthu")
    public String thongKeDoanhThu(Model model,@RequestParam(value="tk",required = false, defaultValue = "0")int tk) {

      
       model.addAttribute("doanhThu", this.bieuDoService.thongKeDoanhThu(tk));
       model.addAttribute("type", tk);

       return "adminThongKeDoanhThu";
    }


@GetMapping("/matdo")
    public String thongKeMatDo(Model model) {

        
       model.addAttribute("matDo", this.bieuDoService.matDoChuyenXe());

       return "adminThongKeMatDo";
    }    
  
    
}
