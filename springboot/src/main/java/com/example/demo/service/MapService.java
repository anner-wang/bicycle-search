package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Image;
import com.example.demo.bean.database.ImageDatabase;

@Service
public class MapService {
	@Autowired
	private ImageDatabase imageDatabase;
	public List<Image> getTodayImage(){
		String currentTime=new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		List<Image>images=imageDatabase.getImage(currentTime);
		return images;
	}
	public List<Image> getYesterdayImage(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		String currentTime=new SimpleDateFormat("yyyy年MM月dd日").format(cal.getTime());
		List<Image>images=imageDatabase.getImage(currentTime);
		return images;
	} 
	/*public static void main(String []args) {
		MapService service=new MapService();
		service.getYesterdayImage();
	}*/
}
