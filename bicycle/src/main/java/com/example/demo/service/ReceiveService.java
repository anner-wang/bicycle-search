package com.example.demo.service;	

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.bean.Image;
import com.example.demo.bean.User;
import com.example.demo.bean.database.ImageDatabase;
import com.example.demo.bean.database.UserDataBase;

@Service
public class ReceiveService {
	@Autowired
	private UserDataBase userDataBase;
	
	@Autowired
	private ImageDatabase imageDatabase;
	private static String fileName;
	//���õ��ܺ���
	public void doImageReceive(MultipartFile file) {
		fileName=Long.toString(new Date().getTime());
		//����ͼƬ���ļ���
		saveImage(file,fileName);
	}
	public void doDataReceive(HttpServletRequest request) {
		//���user����
		User user=getUser(request);
		//����user
		if(user==null) {
			return;
		}
		addUser(user);
		//���image����
		Image image=getImage(request);
		addImage(image);
	}
	public void saveImage(MultipartFile file,String fileName) {
		String path="/user/file/images/"+fileName+".jpg";
			File image=new File(path);
			try {
				file.transferTo(image);
			}catch(Exception e){
				//console.writeToDesktop("�쳣:"+e.getMessage());
			}
		//	console.writeToDesktop("�ļ� "+fileName+".jpg д��"+path+"�ɹ�");
		
	}
	public void addUser(User user) {
		//�ж�user�Ƿ����
		if(userDataBase.getUser(user.getName())!=null) {
			userDataBase.updateUser(userDataBase.getUser(user.getName()));
		}
		else {
			userDataBase.addToUserTable(user);
		}
	}
	public void addImage(Image image) {
		imageDatabase.addImage(image);
	}
	public User getUser(HttpServletRequest request) {
		String name=request.getParameter("name");
		int uploadNumber=1;
		String status="normal";
		if(name==null) {
			return null;
		}
		User user=new User(name,uploadNumber,status);
		return user;
	}
	public Image getImage(HttpServletRequest request) {
		String currentTime=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		String currentDate=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		String name=request.getParameter("name");
		String longitude=request.getParameter("longitude");
		String latitude=request.getParameter("latitude");
		int number=0;
		String ps=request.getParameter("ps");
		return new Image(name,currentTime,longitude,latitude,fileName+".jpg",number,ps);	
	}
}
