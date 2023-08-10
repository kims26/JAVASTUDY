package com.example.demo_board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo_board.dao.CommentDao;
import com.example.demo_board.vo.CommentVo;

import util.MyConstant;

@Controller
@RequestMapping("/board/")
public class CommentController {
    
    @Autowired
    HttpServletRequest request;

    CommentDao commentDao;

    @Autowired
    public CommentController(CommentDao commentDao) {
        this.commentDao = commentDao;
    }


    // /board/comment_list.do?b_idx=5&page=1
    // 댓글목록 가져오기
    @RequestMapping("comment_list.do")
    public String comment_list(int b_idx,
                               @RequestParam(name="page",defaultValue = "1")int nowPage,
                               Model model){


        Map<String,Object> map = new HashMap<String,Object>();

        //검색범위 구하기
        int start = (nowPage-1)*MyConstant.Comment.BLOCK_LIST + 1 ; 
        int end   = start + MyConstant.Comment.BLOCK_LIST - 1;

        //검색조건을 map에 포장
        map.put("b_idx", b_idx);
        map.put("start", start);
        map.put("end", end);


        List<CommentVo> list = commentDao.selectList(map);

        model.addAttribute("list", list);


        return "board/comment_list"; // /WEB-INF/views/board/comment_list.jsp
    }



    @RequestMapping("comment_insert.do")
    @ResponseBody
    public Map<String,String> comment_insert(CommentVo vo){

        String comment_ip = request.getRemoteAddr();
        vo.setComment_ip(comment_ip);

        // \n -> <br>
        String comment_content = vo.getComment_content()
                                   .replaceAll("\n", "<br>");
        vo.setComment_content(comment_content);                                   

        int res = commentDao.insert(vo);
        Map<String,String> map = new HashMap<String,String>();
        
        if(res==1){
          map.put("result","success");
        }else{
          map.put("result","fail");  
        }

        return map;
    }

    @RequestMapping("comment_delete.do")
    @ResponseBody
    public Map<String,Object> comment_delete(int b_idx,int page,String search,String search_text,RedirectAttributes ra){

        int res = commentDao.delete();
        Map<String,Object> map = new HashMap<String,Object>();
        
        if(res==1){
          map.put("result","success");
        }else{
          map.put("result","fail");  
        }

        return map;
    }




}
