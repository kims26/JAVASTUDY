package com.example.demo_board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_board.vo.BoardVo;

@Mapper
public interface BoardDao {
    
    List<BoardVo> selectList();

    List<BoardVo> selectConditionList(Map<String,Object> map);

    BoardVo selectOne(int b_idx);
    
    //전체게시물수
    int selectRowTotal(Map<String,Object> map);

    //새글쓰기
    int  insert(BoardVo vo);

    //답글쓰기
    int  reply(BoardVo vo);

    //조회수 증가
    int  update_readhit(int b_idx);

    //기준글보다 b_step이 큰게시물의 b_step 1씩 증가
    int update_step(BoardVo baseVo);
    
    //수정
    int update(BoardVo vo);

    //삭제
    int delete_update_b_use(int b_idx);



}
