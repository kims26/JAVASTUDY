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

    public PhotoController(PhotoDao visitDao) {
        this.photoDao = photoDao;
    }


    @RequestMapping("/photo/list.do")
	public String list(PhotoVo vo) {

		List<PhotoVo> list = photoDao.selectList();
		
		//request binding
		request.setAttribute("list", list);
		
		return "photo_list.jsp";
	}



    
}
