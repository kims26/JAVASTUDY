package com.example.demo_transaction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo_transaction.dao.ProductInDao;
import com.example.demo_transaction.dao.ProductOutDao;
import com.example.demo_transaction.dao.ProductRemainDao;
import com.example.demo_transaction.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {

    ProductInDao     productInDao;     //입고
    ProductOutDao    productOutDao;    //출고
    ProductRemainDao productRemainDao; //재고

    
    public ProductServiceImpl(ProductInDao productInDao, ProductOutDao productOutDao,
            ProductRemainDao productRemainDao) {
        this.productInDao = productInDao;
        this.productOutDao = productOutDao;
        this.productRemainDao = productRemainDao;
    }


    @Override
    public int insert_in(ProductVo vo) throws Exception {
        
        //1.입고등록
        int res = productInDao.insert(vo);

        //2.현재입고상품이 재고테이블에 있는지 여부
        ProductVo remainVo = productRemainDao.selectOne(vo.getName());

        if(remainVo==null){
            //재고목록에 현재 입고상품이 없는경우=>재고테이블에 등록
            res = productRemainDao.insert(vo);
        }else{
            //이미등록된 상품이 있으면=>수정
            // 재고수량 = 재고수량 + 입고수량
            int cnt = remainVo.getCnt() + vo.getCnt();
            remainVo.setCnt(cnt);

            //재고수량 수정
            res = productRemainDao.update(remainVo);

        }


        return res;
    }

    @Override
    //해당메소드내에서 Exception발생시 rollback된다
    @Transactional(rollbackFor = Exception.class)
    public int insert_out(ProductVo vo) throws Exception {
        
        //1.출고등록
        int res = productOutDao.insert(vo);

        //2.현재출고상품정보를 재고테이블에서 얻어온다
        ProductVo remainVo = productRemainDao.selectOne(vo.getName());

        if(remainVo==null){
            // 출고한 상품이 재고테이블에 없는경우
            throw  new Exception("remain_not");
        }else{
            // 재고테이블에 있는경우
            // 재고수량 = 재고수량 - 출고수량
            int cnt = remainVo.getCnt() - vo.getCnt();
            
            //출고수량 > 재고수량
            if(cnt<0){
                throw  new Exception("remain_lack");
            }
            
            remainVo.setCnt(cnt);

            res = productRemainDao.update(remainVo);

        }

        return res;
    }
    @Override
    public Map<String, List<ProductVo>> selectList() {
        

        //입고목록
        List<ProductVo> in_list  = productInDao.selectList();
        //출고목록
        List<ProductVo> out_list = productOutDao.selectList();
        //재고목록
        List<ProductVo> remain_list = productRemainDao.selectList();

        Map<String, List<ProductVo>> map = new HashMap<String,List<ProductVo>>();

        map.put("in_list", in_list);
        map.put("out_list",out_list);
        map.put("remain_list", remain_list);

        return map;
    }

    
    
    

}
