package com.creation.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.creation.database.ImageDatabase;
import com.creation.model.Image;

public class ThermalMapService {
	private ImageDatabase imageDatabase=new ImageDatabase();
	public List<Image>getHourImages(int index){
		String str;
		if(index<10) {
			str="0"+index;
		}else {
			str=Integer.toString(index);
		}
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		String time=new SimpleDateFormat("yyyyÄêMMÔÂddÈÕ ").format(c.getTime())+str+":";
		List<Image>images=imageDatabase.getImage(time);
		return images;
	}
}
