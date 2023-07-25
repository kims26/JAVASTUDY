package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDao;
import dao.NoticeDao;

@Service
public class BoardServiceImpl implements BoardService {

    //Injection방식
    //1.Construcor Injection(생성자를 통해서 주입):코딩단계에서 에러 체크
    //2.Setter Injection(setter 통해서 주입):실행시 에러발생
    //3.Field Injection                    :실행시 에러발생 


    BoardDao dao;

    //Field Injection
    @Autowired
    NoticeDao noticeDao;

    // public BoardServiceImpl() {
        
    //     System.out.println("--2.BoardServiceImpl()--");
    // }
    
    //생성자가 1개만 있을경우에는 생략가능하다(@Autowired)
    @Autowired
    public BoardServiceImpl(BoardDao dao) {
        this.dao = dao;
        System.out.println("--2.BoardServiceImpl(dao)--");
    }



    @Override
    public Map selectTotalList() {
        // TODO Auto-generated method stub
        List boardList  = dao.selectList();
        List noticeList = noticeDao.selecList();
        Map<String,List> map = new HashMap<String,List>();

        map.put("board", boardList);
        map.put("notice", noticeList);


        return map;
    }
    
    
}
