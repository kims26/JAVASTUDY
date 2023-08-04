package com.example.demo_board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo_board.dao.BoardDao;
import com.example.demo_board.vo.BoardVo;
import com.example.demo_board.vo.MemberVo;

@Controller
@RequestMapping("/board/")
public class BoardController {
    

    @Autowired
    HttpSession session;

    @Autowired
    HttpServletRequest request;
    

    BoardDao boardDao;

    @Autowired   // <- 생략가능하다(가급적이면 써주는게 좋다)
    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    //@RequestMapping("/board/list.do")
    @RequestMapping("list.do")  //  위에 "/board/" + "list.do"
    public String list(Model model){

        List<BoardVo> list = boardDao.selectList();


        //이전 view.do에서 session저장해놓은 show값 지우기
        session.removeAttribute("show");


        //model->DispatcherServlet전달->request binding
        //     ->return되는 뷰정보를 이용해서 forward 
        model.addAttribute("list", list);

        return "board/board_list";
    }

   

    //  /board/view.do?b_idx=4
    @RequestMapping("view.do")
    public String view(int b_idx,Model model){

        //Session에 show값이 있냐?
        if(session.getAttribute("show")==null){
            //조회수 증가
            int res = boardDao.update_readhit(b_idx);
            if(res==0){}

            session.setAttribute("show", true);
        }

        BoardVo vo = boardDao.selectOne(b_idx);
        //결과적으로 request binding
        model.addAttribute("vo", vo);

        return "board/board_view";
    }

    //새글쓰기 폼
    @RequestMapping("insert_form.do")
    public String insert_form(){

        return "board/board_insert_form";
    }


    // /board.do?b_subject=제목&b_content=내용
    //새글쓰기
    @RequestMapping("insert.do")
    public String insert(BoardVo vo,RedirectAttributes ra){

        //로그인 유저정보 구하기
        MemberVo user = (MemberVo) session.getAttribute("user");

        //로그아웃된 상태면
        if(user==null){
            
            ra.addAttribute("reason","fail_session_timeout");
            //login_form.do?reason=fail_session_timeout 
            return "redirect:../member/login_form.do";
        }

        // 로그인된 유저정보를 vo에 넣는다
        vo.setMem_idx(user.getMem_idx());
        vo.setMem_name(user.getMem_name());
        
        String b_ip = request.getRemoteAddr();
        vo.setB_ip(b_ip);

        // \n -> <br>
        String b_content = vo.getB_content().replaceAll("\n", "<br>");
        vo.setB_content(b_content);

        //DB Insert
        int res = boardDao.insert(vo);
        if(res==0){}

        
        return "redirect:list.do";
    }


    //  /board/reply_form.do?b_idx=6

    //답글쓰기 폼
    @RequestMapping("reply_form.do")
    public String reply_form(int b_idx){

        return "board/board_reply_form";
    }


    //답글쓰기
    //  /board/reply.do?b_idx=6&b_subject=제목&b_content=내용
     @RequestMapping("reply.do")
     public String reply(BoardVo vo,RedirectAttributes ra){

        //로그인 유저정보 구하기
        MemberVo user = (MemberVo) session.getAttribute("user");

        //로그아웃된 상태면
        if(user==null){
            
            ra.addAttribute("reason","fail_session_timeout");
            //login_form.do?reason=fail_session_timeout 
            return "redirect:../member/login_form.do";
        }

        // 로그인된 유저정보를 vo에 넣는다
        vo.setMem_idx(user.getMem_idx());
        vo.setMem_name(user.getMem_name());
        
        String b_ip = request.getRemoteAddr();
        vo.setB_ip(b_ip);

        // \n -> <br>
        String b_content = vo.getB_content().replaceAll("\n", "<br>");
        vo.setB_content(b_content);


        //1.b_idx에 해당되는 기준글 정보 얻어오기
        BoardVo baseVo  = boardDao.selectOne(vo.getB_idx());

        //2.기준글보다 b_step큰 게시물의 b_step를 1씩 증가
        int res = boardDao.update_step(baseVo);
        
        if(res==0){}
        
        //3.현재 등록글에 대한 b_ref,b_step,b_depth설정작업
        vo.setB_ref(baseVo.getB_ref());       //그대로설정
        vo.setB_step(baseVo.getB_step()+1);   //기준글+1
        vo.setB_depth(baseVo.getB_depth()+1); //기준글+1

        //4.DB reply
        res = boardDao.reply(vo) ;


        return "redirect:list.do";
     }



     //  /board/modify_form.do?b_idx=6

    //수정 폼
    @RequestMapping("modify_form.do")
    public String modify_form(int b_idx,Model model){

       // BoardVo baseVo  = boardDao.selectOne(vo.getB_idx());

        BoardVo vo = boardDao.selectOne(b_idx);

   

        model.addAttribute("vo", vo);

        return "board/board_modify_form";
    }

    @RequestMapping("modify.do")
    public String modify(BoardVo vo){

    String b_ip = request.getRemoteAddr();
    vo.setB_ip(b_ip);

    String b_content = vo.getB_content().replaceAll("\n", "<br>");
    vo.setB_content(b_content);

        int res=boardDao.update(vo);

        if(res==0){}

        return "redirect:list.do";
    }


    


}
