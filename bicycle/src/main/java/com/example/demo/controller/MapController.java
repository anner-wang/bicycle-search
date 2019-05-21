package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.bean.Image;
import com.example.demo.bean.User;
import com.example.demo.service.MapService;

@Controller
@RequestMapping("/map")
public class MapController {
	private MapService mapService=new MapService();
	public static int count=0;
	@RequestMapping("/index")
	public ModelAndView map() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("map");
		return modelAndView;
	}
	@RequestMapping("/getPoint")
	public @ResponseBody Image getPoints(@RequestBody User user){
		List<Image> images=mapService.getYesterdayImage();
		Image image=new Image("123","12345","123","123","123",123,"123");
		if(count<images.size()) {
			image=images.get(count);
			count++;
		}
		return image;
	}
}
