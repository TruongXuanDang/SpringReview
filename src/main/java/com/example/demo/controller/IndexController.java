package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
//    @Autowired
//        private RoleService roleService;
        @RequestMapping("/")
        public String index(Model model){
//            List<Role> lsRole = roleService.getAll();
//            model.addAttribute("lsRole",lsRole);
            return "index";
        }

        @RequestMapping("/ajax")
        public String ajax(Model model) {

            return "ajax";
        }
}
