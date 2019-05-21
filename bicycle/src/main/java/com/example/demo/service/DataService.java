package com.example.demo.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Image;
import com.example.demo.bean.User;
import com.example.demo.bean.database.ImageDatabase;
import com.example.demo.bean.database.UserDataBase;

@Service
public class DataService {
	@Autowired
	private UserDataBase userDataBase;
	@Autowired
	private ImageDatabase imageDataBase;
	public List<User> getAllUsers() {
		return userDataBase.getUsers();
	}
	public List<Integer>getDayUploadNumberList() {
		String time=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		List<Image>images=imageDataBase.getImage(time);
		List<Integer>list=new ArrayList<Integer>();
		int []numbers=new int[24];
		for(int i=0;i<24;i++) {
			numbers[i]=0;
		}
		for(Image image:images) {
			int index=Integer.parseInt(image.getTime().substring(12, 14));
			numbers[index]++;
		}
		for (int num : numbers) {
			list.add(num);
		}
		return list;
	}
	public List<Integer>getMonthUploadNumber(){
		List<Integer>list=new ArrayList<Integer>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -30);
        List<String>times=new ArrayList<String>();
        for(int i=0;i<30;i++) {
        	c.add(Calendar.DATE,+1);
        	String time = format.format(c.getTime());
        	List<Image>images=imageDataBase.getImage(time);
        	list.add(images.size());
        }
        return list;
	}
	public static void main(String[] args) {
		DataService service=new DataService();
		service.getMonthUploadNumber();
	}
}
