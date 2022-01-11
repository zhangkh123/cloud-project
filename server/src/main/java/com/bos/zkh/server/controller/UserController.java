package com.bos.zkh.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping("/user/hello")
    public String hello(){
        return "hello";
    }

}
