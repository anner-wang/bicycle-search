package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Image;
import com.example.demo.service.ImageService;

@Controller
@RequestMapping("/analyseData")
public class ImageController {
	private ImageService analyseDataService=new ImageService();
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/image");
		
		List<String>imageNames=analyseDataService.getThreedaysImageName();
		request.setAttribute("images", imageNames);
		MapController.count=0;
		return modelAndView;
	}
	@RequestMapping("/getImageData")
	public @ResponseBody String getImageData(@RequestBody String id) {
		Image image=analyseDataService.getImageDataByName(id);
		return JSON.toJSONString(image);
	}
	@RequestMapping("/setImageData")
	public @ResponseBody String setImageData(@RequestBody Image image) {
		if(analyseDataService.changeImage(image)) {
			return "ok";
		}
		return "wrong";
	}
}
