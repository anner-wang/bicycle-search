package com.creation.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.creation.database.ImageDatabase;
import com.creation.database.UserDataBase;
import com.creation.model.Image;
import com.creation.model.UploadVisitedNumber;
import com.creation.model.User;

public class UserService {
	private UserDataBase userDataBase=new UserDataBase();
	private ImageDatabase imageDatabase=new ImageDatabase();
	public List<User>getAllUsers(){
		List<User>users=userDataBase.getUser();
		return users;
	}
	//��ȡ�û����ϴ����ͷ�������Ϣ
	public List<UploadVisitedNumber>getUploadVisitedNumbers(){
		Calendar calendar=Calendar.getInstance();
		List<UploadVisitedNumber>uploadVisitedNumbers=new ArrayList<UploadVisitedNumber>();
		for(int i=0;i<10;i++) {
			String time;
			UploadVisitedNumber uploadVisitedNumber=new UploadVisitedNumber();
			//�������������
			if(i==0)time=new SimpleDateFormat("yyyy��MM��dd��").format(new Date());
			else {
				calendar.add(Calendar.DATE,-1);
				time=new SimpleDateFormat("yyyy��MM��dd��").format(calendar.getTime());
			}
			List<Image>images=imageDatabase.getImage(time);
			uploadVisitedNumber.setTime(time);
			uploadVisitedNumber.setUploadNumber(images.size());
			//ȷ�����ϴ���
			int count=0;
			Map<String,Integer>map=new Hashtable<String,Integer>();
			for(Image image:images) {
				if(map.containsKey(image.getName())) {
					map.put(image.getName(), map.get(image.getName())+1);
				}else {
					map.put(image.getName(), 1);
				}
			}
			uploadVisitedNumber.setVisitedNumber(map.size());
			uploadVisitedNumbers.add(uploadVisitedNumber);
		}
		return uploadVisitedNumbers;
	}
	/*public static void main(String []args) {
		UserService userService=new UserService();
		userService.getUploadVisitedNumbers();
	}*/
}
