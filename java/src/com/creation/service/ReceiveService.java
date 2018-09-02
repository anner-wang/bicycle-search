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
	//调用的总函数
	public void doImageReceive(MultipartFile file) {
		fileName=Long.toString(new Date().getTime());
		//保存图片到文件夹
		saveImage(file,fileName);
	}
	public void doDataReceive(HttpServletRequest request) {
		//添加user对象
		User user=getUser(request);
		//过滤user
		if(user==null) {
			return;
		}
		addUser(user);
		//添加image对象
		Image image=getImage(request);
		addImage(image);
	}
	public void saveImage(MultipartFile file,String fileName) {
		String path="/user/file/images/"+fileName+".jpg";
			File image=new File(path);
			try {
				file.transferTo(image);
			}catch(Exception e){
				//console.writeToDesktop("异常:"+e.getMessage());
			}
		//	console.writeToDesktop("文件 "+fileName+".jpg 写入"+path+"成功");
		
	}
	public void addUser(User user) {
		//判断user是否存在
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
		String currentTime=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
		String currentDate=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		String name=request.getParameter("name");
		String longitude=request.getParameter("longitude");
		String latitude=request.getParameter("latitude");
		int number=0;
		String ps=request.getParameter("ps");
		//测试是否执行shell
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
