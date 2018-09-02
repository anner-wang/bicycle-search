package com.creation.service;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.creation.database.ImageDatabase;
import com.creation.database.UserDataBase;
import com.creation.model.Image;
import com.creation.model.User;

public class DataService {
	private UserDataBase userDataBase=new UserDataBase();
	private ImageDatabase imageDataBase=new ImageDatabase();
	public List<User> getAllUsers() {
		return userDataBase.getUser();
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
