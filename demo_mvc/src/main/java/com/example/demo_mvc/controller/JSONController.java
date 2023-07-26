package com.example.demo_mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.PersonVo;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class JSONController {
    
    public JSONController() {
        System.out.println("--JSONController()--");
    }

    // Map -> JSON형식으로 반환
    @RequestMapping("/map.do")
    @ResponseBody
    public Map<String,String> map(){

        Map <String,String> map = new HashMap<String,String>();
        map.put("name", "홍길동");
        map.put("age", "20");
        map.put("addr", "서울시 관악구 시흥대로 552");

        return map;
    }

    // Array or ArrayList -> JSON형식으로 반환
    @RequestMapping("/array_list.do")
    @ResponseBody
    public List<String> array_list(){

        List<String> list = new ArrayList<String>();
        list.add("사과");
        list.add("참외");
        list.add("수박");
        list.add("딸기");
        list.add("포도");
        
        return list;
    }

    //PersonVo객체 -> JSON형식
    @RequestMapping("/person.do")
    @ResponseBody
    public PersonVo person(){

        PersonVo p = new PersonVo();
        p.setName("일길동");
        p.setAge(10);
        p.setAddr("서울시 관악구 시흥대로 552");

        return p;
    }

    //PersonList반환-> JSON반환
    @RequestMapping(value="/person_list.do" , 
                    produces="application/json;charset=utf-8;")
    @ResponseBody
    public List<PersonVo> person_list(){

        List<PersonVo> p_list  = new ArrayList<PersonVo>();
        p_list.add( new PersonVo("일길동", 21, "서울 관악구"));
        p_list.add( new PersonVo("이길동", 22, "서울 강남구"));
        p_list.add( new PersonVo("삼길동", 23, "서울 강북구"));
        p_list.add( new PersonVo("사길동", 24, "서울 강동구"));
        p_list.add( new PersonVo("오길동", 25, "서울 강서구"));

        return p_list;
    }

    
    


}
