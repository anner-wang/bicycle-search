package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.User;
import com.example.demo.bean.database.UserDataBase;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired 
	UserDataBase userDataBase;
	//测试
	@ResponseBody
	@RequestMapping("/allusers")
	public String getAllUsers() {
		return JSON.toJSONString(userDataBase.getUsers());
	}
	@ResponseBody
	@RequestMapping("/insertuser")
	public String insertUser() {
		User user=new User("anner",123,"normal");
		userDataBase.addToUserTable(user);
		return JSON.toJSONString(userDataBase.getUsers());
	}
}
