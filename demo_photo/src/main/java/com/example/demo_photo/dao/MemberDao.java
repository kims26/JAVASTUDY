package com.example.demo_photo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo_photo.vo.MemberVo;

public interface MemberDao {

     
    List<MemberVo> selectList();//전체조회
    
    MemberVo selectOne(int idx);

    List<MemberVo> selectConditionList(Map map);

    int insert(MemberVo vo);
     

     
}
