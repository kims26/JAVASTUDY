package com.example.demo_photo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo_photo.dao.PhotoDao;
import com.example.demo_photo.vo.PhotoVo;

public class PhotoController {
    

    
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    PhotoDao photoDao;

    public PhotoController(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }


    @RequestMapping("/photo/list.do")
	public String list(PhotoVo vo) {

		List<PhotoVo> list = photoDao.selectList();
		
		//request binding
		request.setAttribute("list", list);
		
		return "photo_list.jsp";
	}

   //입력폼 띄우기
   @RequestMapping("/photo/insert_form.do")
   public String insert_form(){

       return "photo/photo_insert_form";
   }


   @RequestMapping("/photo/delete.do")

    public String delete(int p_idx){
    
        
        int res = photoDao.delete(p_idx);

        return "redirect:list.do";
    }

     @RequestMapping("/photo/update.do")

    public String update(PhotoVo vo){
    
        
        int res = photoDao.update(vo);

        return "redirect:list.do";
    }


    

    
   








    
}
