package com.example.demo_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_board.vo.BoardVo;

@Mapper
public interface BoardDao {
    
    List<BoardVo> selectList();



}
