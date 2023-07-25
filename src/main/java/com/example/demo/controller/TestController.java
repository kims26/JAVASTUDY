package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    
    public TestController(){

        System.out.println("--TestController()--");

    }


    @RequestMapping("/hello.do")
    @ResponseBody
    public String hello() {
        return "안녕하세요 반갑습니다";
    }

    


}
