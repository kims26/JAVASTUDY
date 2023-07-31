package com.example.demo_photo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo_photo.vo.PhotoVo;

public interface PhotoDao {
    
     List<PhotoVo> selectList();//전체조회
    
    PhotoVo selectOne(int idx);

     List<PhotoVo> selectConditionList(Map map);

     int insert(PhotoVo vo);
     

     

}
