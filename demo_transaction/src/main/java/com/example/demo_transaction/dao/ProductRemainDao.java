package com.example.demo_transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_transaction.vo.ProductVo;

@Mapper
public interface ProductRemainDao {
    
    List<ProductVo> selectList();
    ProductVo selectOne(String name);
    int insert(ProductVo vo);
    int update(ProductVo vo);

    //수정/삭제는 추후 추가할것

}
