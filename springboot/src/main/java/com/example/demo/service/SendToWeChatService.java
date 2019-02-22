package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.bean.Image;
import com.example.demo.bean.database.ImageDatabase;

@Service
public class SendToWeChatService {
	@Autowired
	private ImageDatabase imageDatabase;
	public void doSend(HttpServletRequest request,HttpServletResponse response) {
		List<Image> images=imageDatabase.findImage(request.getParameter("name"));
		JSONArray jsonArray=new JSONArray();
		for(Image image:images) {
			jsonArray.add(image);
		}
		try {
			response.setContentType("text/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonArray.toJSONString());
			response.flushBuffer();
		//	console.writeToDesktop(images.size()+"");
	//		console.writeToDesktop("����΢�Ŷ˵�json����Ϊ:"+jsonArray.toJSONString());
		}catch(Exception e) {
		//	console.writeToDesktop("���ݷ��ص�΢�ų����ʧ�ܣ���ϸ��ϢΪ"+e.getMessage());
		}
	}
	/*public static void main(String []args) {
		Map<String,Object>map=new HashMap<String ,Object>();
		map.put("length",12);
		JSONObject jsonObject=new JSONObject(map);
		System.out.println(jsonObject.toJSONString());
	}*/
}
