package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
        private UserService userService;
        @RequestMapping("/user")
        public String index(Model model){
            List<User> lsUser = userService.getAll();
            model.addAttribute("lsUser",lsUser);
            return "user/index";
        }

        @RequestMapping("/add")
        public String addRole(Model model){
            model.addAttribute("user",new User());
            return "user/add";
        }

        @RequestMapping(value = "save",method = RequestMethod.POST)
        public String save(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request,Model model){
            int userId  = request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
            if(bindingResult.hasErrors()){
                if(userId==0)
                    return "user/add";
                else {
                    Optional<User> userEdit = userService.findUserById(userId);
                    userEdit.ifPresent(u->model.addAttribute("user",user));
                    return "user/edit";
                }

            }
            else {
                userService.saveUser(user);
                return "redirect:/user";
            }
        }

        @RequestMapping("/edit")
        public String editUser(@RequestParam("id") int userId, Model model){
            Optional<User> userEdit = userService.findUserById(userId);
            userEdit.ifPresent(user->model.addAttribute("user",user));
            return "user/edit";
        }

        @RequestMapping("/delete")
        public String deleteUser(@RequestParam("id") int userId, Model model){
            userService.deleteUser(userId);
            return "redirect:/user";
        }
}
