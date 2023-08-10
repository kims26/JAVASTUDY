package com.example.demo_board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_board.vo.CommentVo;

@Mapper
public interface CommentDao {
   
    List<CommentVo> selectList(Map<String,Object> map);
    int insert(CommentVo vo);
    int delete(int comment_idx);

}
