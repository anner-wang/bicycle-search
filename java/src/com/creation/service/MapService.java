package com.creation.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.creation.database.ImageDatabase;
import com.creation.model.Image;

public class MapService {
	private ImageDatabase imageDatabase=new ImageDatabase();
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
