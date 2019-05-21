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
import com.example.demo.bean.User;
import com.example.demo.service.DataService;
@Controller
@RequestMapping("/data")
public class DataController {
	private DataService dataService=new DataService();
	@RequestMapping("/index")
	public ModelAndView getData(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView=new ModelAndView();
		MapController.count=0;
		modelAndView.setViewName("/data");
		return modelAndView;
	}
	@RequestMapping("/userUploadNumber")
	public @ResponseBody String userUploadNumber(@RequestBody String test) {
		List<User>users=dataService.getAllUsers();
		return JSON.toJSONString(users);
	}
	@RequestMapping("/dayUploadNumber")
	public @ResponseBody String dayUploadNumber(@RequestBody String test) {
		return JSON.toJSONString(dataService.getDayUploadNumberList());
	}
	@RequestMapping("/monthUploadNumber")
	public @ResponseBody String monthUploadNumber(@RequestBody String test) {
		return JSON.toJSONString(dataService.getMonthUploadNumber());
	}
}
