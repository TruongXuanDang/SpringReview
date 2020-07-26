package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserValidator;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
        private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

        @RequestMapping("/user")
        public String index(Model model){
            List<User> lsUser = userService.getAll();
            model.addAttribute("lsUser",lsUser);

            return "user/index";
        }

        @RequestMapping("/add")
        public String addRole(Model model){
            model.addAttribute("user",new User());
            List<Role> lsRole = roleService.getAll();
            model.addAttribute("lsRole",lsRole);
            return "user/add";
        }

        @RequestMapping(value = "save",method = RequestMethod.POST)
        public String save(@Validated @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, HttpServletRequest request, Model model){
//            new UserValidator().validate(userDTO,bindingResult);
            if(bindingResult.hasErrors()){
                    List<Role> lsRole = roleService.getAll();
                    model.addAttribute("lsRole",lsRole);
                    return "user/add";
            }
            else {
                String[] roles = userDTO.getRoles();
                Set<Role> _roles = new HashSet<>();
                if(roles!=null)
                {
                    for(String r:roles){
                        Role role = roleService.findRoleById(Integer.parseInt(r));
                        _roles.add(role);
                    }
                }

                User user = new User();
                user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
                user.setPhone(userDTO.getPhone());
                user.setRoles(_roles);
                user.setUsername(userDTO.getUsername());
                userService.saveUser(user);
                return "redirect:/user";
            }
        }

        @RequestMapping(value = "saveedit",method = RequestMethod.POST)
        public String saveedit(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request,Model model){
            int userId  = request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
            if(bindingResult.hasErrors()){
                Optional<User> userEdit = userService.findUserById(userId);
                userEdit.ifPresent(u->model.addAttribute("user",userEdit));
                return "user/edit";
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
