package com.example.demo_sawon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_sawon.vo.SawonVo;

@Mapper
public interface SawonDao {
    
    List<SawonVo> selectList();
}
