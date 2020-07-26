package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/pagination")
    //public String pagination(Model model, @RequestParam("page") int page, @RequestParam("size") int size){
    public String pagination(Model model, HttpServletRequest request){
        int page  = request.getParameter("page")==null?0:Integer.parseInt(request.getParameter("page"));
        int size  = request.getParameter("size")==null?1:Integer.parseInt(request.getParameter("size"));
        Pageable pageable = PageRequest.of(page, size);
        Page<Role> lsRole = roleService.findAll(pageable);
        model.addAttribute("lsRole",lsRole);
        return "pagination";
    }
}
