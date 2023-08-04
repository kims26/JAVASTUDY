package com.example.demo_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_board.vo.MemberVo;



@Mapper
public interface MemberDao {

	public List<MemberVo> selectList();
	
	public MemberVo selectOneFromIdx(int mem_idx);
	
	public MemberVo selectOneFromId(String mem_id);
	
	public int insert(MemberVo vo);

	public int update(MemberVo vo) ;

	public int delete(int mem_idx);
	
	
}
