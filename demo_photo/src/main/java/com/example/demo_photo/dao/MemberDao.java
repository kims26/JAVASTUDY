package com.example.demo_photo.dao;

import java.util.List;


import com.example.demo_photo.vo.MemberVo;

public interface MemberDao {

     
    public List<MemberVo> selectList();//전체조회
    
    public MemberVo selectOne(int mem_idx);

    public MemberVo selectOne(String mem_id);

    //List<MemberVo> selectConditionList(Map map);

    public int insert(MemberVo vo);
     
    public int update(MemberVo vo);

    public int delete(int mem_idx);

     
}
