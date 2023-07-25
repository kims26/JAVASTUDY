package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao{

    // 단축키 : Ctrl + .  Override 등 
    //         생성자 : ctor 타이핑 tab tab
    //         import정리 : alt + shift + o          
    public BoardDaoImpl() {
        System.out.println("--1.BoardDaoImpl()--");
    }

    @Override
    public List selectList() {
       
        List<String> list = new ArrayList<String>();
        list.add("내가 1등이야!!");
        list.add("잘했네!!");
        list.add("다음에는 내가 1등해야지!!");

        return list;
    }

   
   
    
    

}
