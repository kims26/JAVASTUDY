package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.BoardService;

@Controller
public class BoardController {
    

    BoardService service;

    public BoardController() {
        System.out.println("--3.BoardController()--");
    }

    @Autowired
    public void setService(BoardService service) {
        this.service = service;
        System.out.println("--3-1.BoardController:setService(service)--");
    }

    @RequestMapping("/board/list.do")
    @ResponseBody
    public String list(){
       
        Map map = service.selectTotalList();

        StringBuffer sb = new StringBuffer("--[게시판목록]--<br>");
        List boardList = (List)map.get("board");
        for(Object title : boardList){
           sb.append((String)title);
           sb.append("<br>");
        }

        
        List notiecList = (List)map.get("notice");
         sb = new StringBuffer("--[공지목록]--<br>");
        for(Object title : notiecList){
           sb.append((String)title);
           sb.append("<br>");
        }

    
        return sb.toString();
    }


    
}
