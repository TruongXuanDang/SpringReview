package com.example.demo.controller;

import com.example.demo.dto.AjaxResponseBody;
import com.example.demo.dto.SearchCriteria;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {
    @Autowired
    UserService userService;

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors){
        AjaxResponseBody result = new AjaxResponseBody();

        if(errors.hasErrors()){
            result.setMsg(
                    errors.getAllErrors()
                            .stream().map(x->x.getDefaultMessage())
                            .collect(Collectors.joining(","))
            );
            return ResponseEntity.badRequest().body(result);
        }

        List<User> users = userService.findByUserName(search.getUsername());
        if(users.isEmpty()){
            result.setMsg("no user found");
        }
        else {
            result.setMsg("success");
        }
        result.setResult(users);
        return ResponseEntity.ok(result);
    }
}
