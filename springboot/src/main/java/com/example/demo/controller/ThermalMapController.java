package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Image;
import com.example.demo.service.ThermalMapService;

@Controller
@RequestMapping("/statisticalData")
public class ThermalMapController {
	private ThermalMapService service=new ThermalMapService();
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/thermalMap");
		return modelAndView;
	}
	@RequestMapping("/getData")
	public @ResponseBody String getData(@RequestBody String index) {
		List<Image>images=service.getHourImages(Integer.parseInt(index));
		return JSON.toJSONString(images);
	}
}
