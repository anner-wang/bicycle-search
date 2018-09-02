package com.creation.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.creation.database.ImageDatabase;
import com.creation.database.UserDataBase;
import com.creation.model.Image;
import com.creation.model.User;

public class ReceiveService {
	private UserDataBase userDataBase=new UserDataBase();
	private ImageDatabase imageDatabase=new ImageDatabase();
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
		if(userDataBase.isUserExist(user)) {
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
		String currentTime=new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss").format(new Date());
		String currentDate=new SimpleDateFormat("yyyy��MM��dd��").format(new Date());
		String name=request.getParameter("name");
		String longitude=request.getParameter("longitude");
		String latitude=request.getParameter("latitude");
		int number=0;
		String ps=request.getParameter("ps");
		//�����Ƿ�ִ��shell
		/*try {
			String path="sh /root/test.sh "+fileName;
			Process p=Runtime.getRuntime().exec(path);
			p.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str=br.readLine();
			number=Integer.parseInt(str);
		}catch(Exception e) {
			
		}*/
		return new Image(name,currentTime,longitude,latitude,fileName+".jpg",number,ps);	
	}
}
