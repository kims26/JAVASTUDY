package com.example.demo_visit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_visit.vo.VisitVo;

@Mapper
public interface VisitDao {
    
    List<VisitVo> selectList();//전체조회
    
    List<VisitVo> selectConditionList(Map<String,String> map);//조건별조회

    VisitVo selectOne(int idx);

    int insert(VisitVo vo);
    int update(VisitVo vo);
    int delete(int idx);

}
