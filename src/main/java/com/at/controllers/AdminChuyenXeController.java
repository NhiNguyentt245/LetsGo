/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.at.controllers;

import com.at.pojo.Chuyenxe;
import com.at.pojo.Taixe;
import com.at.service.ChuyenXeService;
import com.at.service.TaiXeService;
import com.at.service.TuyenXeService;
import com.at.service.XeService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author thu
 */
@Controller
public class AdminChuyenXeController {

    @Autowired
    ChuyenXeService chuyenXeService;

    @Autowired
    TaiXeService taiXeService;

    @Autowired
    XeService xeService;

    @Autowired
    TuyenXeService tuyenXeService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateF, false));
    }

    @RequestMapping("/admin/chuyenxe")
    public String showListchuyenxe(Model model, @RequestParam(value = "kw", defaultValue = "") String kw,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        int activePage = page;

        List<Chuyenxe> ListCX = chuyenXeService.listCX(kw);
        Map<Integer, List<Taixe>> map = taiXeService.listLoaiTX();

        model.addAttribute("keyw", kw);
        model.addAttribute("onePage", 7);
        model.addAttribute("ListChuyenXe", chuyenXeService.getListCXPage(ListCX, page));
        model.addAttribute("countPage", ListCX.size());

        model.addAttribute("ChuyenXe", new Chuyenxe());
        model.addAttribute("ListTaiXeC", map.get(1));
        model.addAttribute("ListTaiXeP", map.get(2));

        model.addAttribute("ListXe", xeService.listXe());
        model.addAttribute("ListTuyenXe", tuyenXeService.getListTuyenXe(null));

        return "adminChuyenXe";
    }

    @PostMapping("/admin/chuyenxe/add")
    public String addchuyenxe(@ModelAttribute(value = "ChuyenXe") Chuyenxe cx) {

        if (chuyenXeService.addChuyenXe(cx)) {
            return "redirect:/admin/chuyenxe";
        }

        return "index";
    }

    @PostMapping("/admin/chuyenxe/update")
    public String updatechuyenxe(@ModelAttribute(value = "ChuyenXe") Chuyenxe cx) {

        if (chuyenXeService.updateCX(cx)) {
            return "redirect:/admin/chuyenxe";
        }

        return "index";
    }

}
