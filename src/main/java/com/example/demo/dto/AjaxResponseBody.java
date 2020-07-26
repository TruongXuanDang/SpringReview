package com.example.demo.dto;

import com.example.demo.entity.User;

import java.util.List;

public class AjaxResponseBody {
    String msg;
    List<User> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }
}
