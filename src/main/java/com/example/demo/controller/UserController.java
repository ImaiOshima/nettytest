package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.annotation.Remote;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2022/1/16 16:31
 * @Created by 61635
 */
@Controller
public class UserController {
    @Remote("getUserNameById")
    public Object getUserNameById(String userId){
        System.out.println("客户端请求的用户id为=====" + userId);
        return "响应结果===用户张三"+userId;
    }
}
