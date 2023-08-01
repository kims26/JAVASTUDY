package com.example.demo_transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo_transaction.vo.ProductVo;

@Mapper
public interface ProductInDao {
    
    List<ProductVo> selectList();
    int insert(ProductVo vo);

    //수정/삭제는 추후 추가할것
    int delete_in(ProductVo vo);
    

}
