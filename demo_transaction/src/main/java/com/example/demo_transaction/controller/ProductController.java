package com.example.demo_transaction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo_transaction.dao.ProductInDao;
import com.example.demo_transaction.service.ProductService;
import com.example.demo_transaction.vo.ProductVo;

@Controller
public class ProductController {
    

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/product/list.do")
    public String list(Model model){


        Map<String,List<ProductVo>> map = productService.selectList();

        model.addAttribute("map", map);

        return "product/product_list"; //View정보
    }

    // /product/insert_in.do?name=TV&cnt=50
    @RequestMapping("/product/insert_in.do")
    public String insert_in(ProductVo vo){

        try {
            productService.insert_in(vo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list.do";
    }

    //  /product/insert_out.do?name=TV&cnt=10
    @RequestMapping("/product/insert_out.do")
    public String insert_out(ProductVo vo,RedirectAttributes ra){

        try {
            productService.insert_out(vo);

        } catch (Exception e) {
            //e.printStackTrace();
            String message = e.getMessage();
            //System.out.println(message);
            //RedirectAttributes  : 결과적으로 redirect:list.do?error=remain_not
            ra.addAttribute("error", message);
        }
        return "redirect:list.do";
    }




    // /product/delete_in.do?idx=9
    @RequestMapping("/product/delete_in.do")
    public String delete_in(ProductVo vo){

        try {
            //productService.delete_in(vo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:list.do";
    }

}
