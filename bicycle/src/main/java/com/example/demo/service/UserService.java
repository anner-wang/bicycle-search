package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Image;
import com.example.demo.bean.UploadVisitedNumber;
import com.example.demo.bean.User;
import com.example.demo.bean.database.ImageDatabase;
import com.example.demo.bean.database.UserDataBase;

@Service
public class UserService {
	@Autowired
	private UserDataBase userDataBase;
	@Autowired
	private ImageDatabase imageDatabase;
	
	
	public List<User>getAllUsers(){
		List<User>users=userDataBase.getUsers();
		return users;
	}

	public List<UploadVisitedNumber>getUploadVisitedNumbers(){
		Calendar calendar=Calendar.getInstance();
		List<UploadVisitedNumber>uploadVisitedNumbers=new ArrayList<UploadVisitedNumber>();
		for(int i=0;i<10;i++) {
			String time;
			UploadVisitedNumber uploadVisitedNumber=new UploadVisitedNumber();
			
			if(i==0)time=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			else {
				calendar.add(Calendar.DATE,-1);
				time=new SimpleDateFormat("yyyy年MM月dd日").format(calendar.getTime());
			}
			List<Image>images=imageDatabase.getImage(time);
			uploadVisitedNumber.setTime(time);
			uploadVisitedNumber.setUploadNumber(images.size());
			
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
}
