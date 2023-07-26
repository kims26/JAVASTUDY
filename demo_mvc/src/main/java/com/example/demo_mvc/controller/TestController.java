package com.example.demo_mvc.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class TestController {
    
    //매 메소드호출시마다 Injection시켜준다
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @Autowired
    ServletContext application;




    public TestController() {
        System.out.println("--TestController()--");
    }


    @RequestMapping("/hello.do")
    public String hello(){

        return "hello"; // /WEB-INF/views/ + hello + .jsp forward
    }


    

    @RequestMapping("/say_hello.do")
    public String say_hello(Model model, RedirectAttributes ra){

        String msg ="Hi~";
        //model통해서 전달 : DispatcherServlet에게 전달
        
        //parameter전달시 RedirectAttributes 이용할것
        ra.addAttribute("msg", msg);
        ra.addAttribute("msg2", "What");
        
        // hello.do?msg=Hi~&msg2=What

        return "redirect:hello.do";
    }


    @RequestMapping("/test.do")
    //public String test(HttpServletRequest request){
    //   DispatcherServlet에서 test메소드 호출시 request넣어줘        
    public String test(Model model){

        //test메소드 인자 : Model model요청


        String ip = request.getRemoteAddr();
       // System.out.println(ip);

        String msg = String.format("[%s]님 안녕하세요", ip);

        //request binding  
        //request.setAttribute("msg", msg);//Spring에서는 이방식 사용 안한다
        model.addAttribute("msg", msg);//결과적으로 request binding

        return "test";
    }





    @RequestMapping("/test2.do")
    public ModelAndView test2(){

        String msg2 = "ModelAndView로 전달된 데이터";

        // Data + View정보를 묶어서 전달하자
        ModelAndView mv = new ModelAndView();

        //Data넣기
        mv.addObject("msg2", msg2);

        //View정보 넣기
        mv.setViewName("test2");

        return mv;
    }
    




}
