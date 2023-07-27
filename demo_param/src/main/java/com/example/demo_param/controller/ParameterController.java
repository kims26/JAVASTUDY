package com.example.demo_param.controller;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.PersonVo;


@Controller
public class ParameterController {
    
    @Autowired
    HttpServletRequest request;


    public ParameterController() {
        System.out.println("--ParameterController()--");
    }

    // insert_old.do?name=홍길동&age=20&addr=서울시 관악구 시흥대로
    @RequestMapping("/insert_old.do")
    public String insert_old(){

        String name = request.getParameter("name");
        int    age  = Integer.parseInt(request.getParameter("age"));
        String addr = request.getParameter("addr");
        System.out.printf("[%s-%d-%s]\n", name,age,addr);
  
        return "";
    }

    // paramter이름 == 수신되는 변수명이 동일할 경우
    // insert1.do?name=홍길동&age=20&addr=서울시 관악구 시흥대로
    //낱개로 받기
    @RequestMapping("/insert1.do")
    @ResponseBody
    public String insert1(String name,int age,String addr){
        //메소드이 인자정보=>DispatcherServlet에 대한 요구사항 

        System.out.printf("[%s-%d-%s]\n", name,age,addr);
        return "낱개로 수신완료";
    }

    // paramter이름 != 수신되는 변수명이 틀린경우 
    // insert11.do?name=홍길동&age=20&addr=서울시 관악구 시흥대로
    //낱개로 받기
    @RequestMapping("/insert11.do")
    @ResponseBody
    public String insert11(
                           @RequestParam("name") String irum,
                           @RequestParam(name="age",defaultValue = "0")  int nai,
                           @RequestParam("addr") String juso,
                           @RequestParam(name="gender",required=false,defaultValue = "남자")    String gender){
        //메소드이 인자정보=>DispatcherServlet에 대한 요구사항 
        //@RequestParam(name="name",required=ture,defaultValue="기본값")
                                    

        System.out.printf("파라메터명 !=수신변수명:[%s-%d-%s-%s]\n", irum,nai,juso,gender);
        return "낱개로 수신완료(파라메터명 != 수신변수명)";
    }

     // insert2.do?name=홍길동&age=20&addr=서울시 관악구 시흥대로

     @RequestMapping("/insert2.do")
     @ResponseBody
     public String insert2(@ModelAttribute PersonVo vo,String name){
            //@ModelAttribute :Spring(DS)에게 PersonVo생성해서 변수 = 파람이름
            //                일치하는 정보를 setter통해서 넣어준다 
            



        return vo.toString();
     }

     //// insert3.do?name=홍길동&age=20&addr=서울시 관악구 시흥대로
        @RequestMapping("/insert3.do")
        @ResponseBody
        public Map insert3(@RequestParam Map<String,String> map){

            //param name 
            Set<String> keySet =(Set<String>)map.keySet();
            for(String key : keySet){

                String value =map.get(key);
                System.out.printf("[%s]:[%s]\n",key,value);
            }

        return map;
     }




}
