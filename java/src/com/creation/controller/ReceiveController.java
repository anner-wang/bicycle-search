package com.creation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creation.service.DataService;
import com.creation.service.ReceiveService;
import com.creation.service.SendToWeChatService;

@Controller
@RequestMapping("/upload")
public class ReceiveController {
	private ReceiveService service=new ReceiveService();
	private SendToWeChatService sendToWeChatService=new SendToWeChatService();
	private DataService getDataService=new DataService();
//	private Chart chart=new Chart();
	@RequestMapping(value="/image",method= {RequestMethod.GET,RequestMethod.POST})
	public void getImage(HttpServletRequest request) {
		//����Ҫ���ص�����
		MultipartHttpServletRequest req=(MultipartHttpServletRequest)request;
		MultipartFile file=req.getFile("file");
		service.doImageReceive(file);
	}
	@RequestMapping(value="/data",method=RequestMethod.GET)
	public void getData(HttpServletRequest request,HttpServletResponse response) {
		service.doDataReceive(request);
//		chart.drawAllChart();
	}
	@RequestMapping(value="/confirm",method=RequestMethod.GET)
	public void sendData(HttpServletRequest request,HttpServletResponse response) {
		sendToWeChatService.doSend(request, response);
	}
}
