package com.example.demo_board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo_board.dao.BoardDao;
import com.example.demo_board.vo.BoardVo;

@Controller
@RequestMapping("/board/")
public class BoardController {
    

    BoardDao boardDao;

    @Autowired   // <- 생략가능하다(가급적이면 써주는게 좋다)
    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    @RequestMapping("list.do")
    public String list(Model model){

        List<BoardVo> list = boardDao.selectList();

        //model->DispatcherServlet전달->request binding
        //     ->return되는 뷰정보를 이용해서 forward 
        model.addAttribute("list", list);

        return "board/board_list";
    }

    @RequestMapping("insert_form.do")
    public String insert_form(){

        return "board/board_insert_form";
    }
}
