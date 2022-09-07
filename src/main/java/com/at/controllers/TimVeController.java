/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Bill;
import com.at.pojo.Chuyenxe;
import com.at.service.ChuyenXeChoNgoiService;
import com.at.service.ChuyenXeService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thu
 */
@Controller
@RequestMapping("/timve")
public class TimVeController {
    
    @Autowired
    ChuyenXeService chuyenXeService;
  
     @GetMapping("/")
    public String timve(@RequestParam (value ="maTX") String tx,
            @RequestParam (value = "date") String date,
            @RequestParam(value="sort", required = false, defaultValue = "-1" ) int s,
            @RequestParam(value="page", required = false, defaultValue = "1") int page,Model model){
        List<Chuyenxe> Listcx = chuyenXeService.listCXSearch(tx, date,s);
       
       List<Chuyenxe> listcxtoPage = chuyenXeService.getListCXPage(Listcx,page);

       
       
        model.addAttribute("ListCX", listcxtoPage);
        model.addAttribute("onePage",7);
        model.addAttribute("countPage",Listcx.size());
        model.addAttribute("gheCon",this.chuyenXeChoNgoiService.listChoNgoiTrong(listcxtoPage));
        model.addAttribute("date",date);
        model.addAttribute("sort",s);
        model.addAttribute("maTuyenXe",tx);   
        return "timve";
    }
    
    
     @Autowired
    ChuyenXeChoNgoiService chuyenXeChoNgoiService;
     
     @GetMapping("/chitiet/{maCX}")
    public String chiTietVe(@PathVariable(value = "maCX") int macx, Model model)
    {
        model.addAttribute("message",0);

        model.addAttribute("Listcxcn1", chuyenXeChoNgoiService.listcxcn(macx,1));
        model.addAttribute("Listcxcn2", chuyenXeChoNgoiService.listcxcn(macx,2));
        model.addAttribute("maCX",macx);
        model.addAttribute("count", chuyenXeChoNgoiService.countColumn(macx));
        model.addAttribute("ListTT",chuyenXeChoNgoiService.listTT());

        return "chiTietVe";
    }
    
    @PostMapping("/chitiet/{maCX}")
    public String checkGhe(@PathVariable(value = "maCX") int macx, Model model,HttpSession session)
    {
        Map<Integer, Bill> b = (Map<Integer, Bill>) session.getAttribute("bill");
        if(b == null)
        {
            return "index";
        }   
        
        List<Bill> listB = b.values().stream().collect(Collectors.toList());
        int k = chuyenXeChoNgoiService.checkCXCN(listB);
   
        if(k == 1)
        {
            return "redirect:/thanhtoan";
        }
        else if(k == 0)
        {
            model.addAttribute("message",1);
           session.setAttribute("bill", null);
           return "redirect:/timve/chitiet/"+macx;
        }
        else
        {
           session.setAttribute("bill", null);
            return "index";
        }
       
        
    }
    
   
    
}
