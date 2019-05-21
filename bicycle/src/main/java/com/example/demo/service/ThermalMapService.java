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
public class ThermalMapService {
	@Autowired
	private ImageDatabase imageDatabase;
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
		String time=new SimpleDateFormat("yyyy年MM月dd日 ").format(c.getTime())+str+":";
		List<Image>images=imageDatabase.getImage(time);
		return images;
	}
}
