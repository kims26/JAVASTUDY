package com.example.demo_photo.dao;

import java.util.List;


import com.example.demo_photo.vo.PhotoVo;

public interface PhotoDao {
    
     List<PhotoVo> selectList();//전체조회
    
   public PhotoVo selectOne(int idx);

    //List<PhotoVo> selectConditionList(Map map);

     public int insert(PhotoVo vo);
     public int update(PhotoVo vo);
     public int delete(int p_idx);
     public int update_filename(PhotoVo vo);
     

     

}
