package com.example.demo_sawon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo_sawon.dao.SawonDao;
import com.example.demo_sawon.vo.SawonVo;

@Controller
public class SawonController {

    @Autowired
    SawonDao sawonDao;

     @RequestMapping("/test/list.do")
    @ResponseBody
    public List<SawonVo> test_list(){

        return sawonDao.selectList();
    }

    public SawonController(SawonDao sawonDao) {
        this.sawonDao = sawonDao;
    }
    
    @RequestMapping("/sawon/list.do")
    public String list(Model model){

    List<SawonVo> list = sawonDao.selectList();
    model.addAttribute("list", list);

// "/WEB-INF/views/" + "dept/dept_list" + ".jsp" 
return "sawon/sawon_list";


    }
}
