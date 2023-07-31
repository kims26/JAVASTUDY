package com.example.demo_visit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_visit.vo.VisitVo;

@Mapper
public interface VisitDao {
    
    List<VisitVo> selectList();//전체조회
    
    VisitVo selectOne(int idx);


    List<VisitVo> selectConditionList(Map map);//조건별조회

    int insert(VisitVo vo);

    int delete(int idx);

    int modify(VisitVo vo);





}
