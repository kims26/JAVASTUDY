package com.example.demo_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_board.vo.BoardVo;

@Mapper
public interface BoardDao {
    
    List<BoardVo> selectList();

    BoardVo selectOne(int b_idx);
    
    //새글쓰기
    int  insert(BoardVo vo);

    //답글쓰기
    int  reply(BoardVo vo);

    //조회수 증가
    int  update_readhit(int b_idx);

    //기준글보다 b_step이 큰게시물의 b_step 1씩 증가
    int update_step(BoardVo baseVo);

    int update(BoardVo vo);





}
