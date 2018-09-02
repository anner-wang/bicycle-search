package com.creation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.creation.model.Image;
import com.creation.service.ThermalMapService;

@Controller
@RequestMapping("/statisticalData")
public class ThermalMapController {
	private ThermalMapService service=new ThermalMapService();
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("/WEB-INF/page/thermalMap.jsp");
		return modelAndView;
	}
	@RequestMapping("/getData")
	public @ResponseBody String getData(@RequestBody String index) {
		List<Image>images=service.getHourImages(Integer.parseInt(index));
		return JSON.toJSONString(images);
	}
}
