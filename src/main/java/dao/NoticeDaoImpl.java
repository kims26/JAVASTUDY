package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    
    public NoticeDaoImpl() {
        System.out.println("--1-1.NoticeDaoImpl()--");
    }

    @Override
    public List<String> selecList() {
        // TODO Auto-generated method stub

        List<String> list = new ArrayList<String>();
        list.add("1.오늘 단수 : 9~12시까지");
        list.add("2.오늘은 여기까지..");
        list.add("3.퇴실체크 잘하고 가세요");

        return list;
    }

    

}
