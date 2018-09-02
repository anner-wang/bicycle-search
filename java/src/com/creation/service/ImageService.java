package com.creation.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.session.SqlSession;

import com.creation.database.ImageDatabase;
import com.creation.model.Image;

public class ImageService {
	private ImageDatabase imageDatabase=new ImageDatabase();
	//获取第一次请求页面的图片数据
	public List<String>getThreedaysImageName(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -1);
		String time=simpleDateFormat.format(c.getTime());
		List<Image>images=imageDatabase.getImage(time);
		List<String>imageNames=new ArrayList<String>();
		for(int i=images.size()-1;i>=0;i--) {
			imageNames.add(images.get(i).getFileName());
		}
		/*Calendar calendar=Calendar.getInstance();
		for(int i=0;i<4;i++) {
			calendar.add(Calendar.DATE, -1);
			currentTime=simpleDateFormat.format(calendar.getTime());
			images=imageDatabase.getImage(currentTime);
			for(Image image:images) {
				imageNames.add(image.getFileName());
			}
		}*/
		return imageNames;
	}
	//根据图片的名称获取图片的信息
	public Image getImageDataByName(String fileName) {
		Image image=imageDatabase.getImageByFileName(fileName);
		return  image;
	}
	//修改图片信息
	public boolean changeImage(Image image) {
		try {
			imageDatabase.changeImage(image);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	/*public static void main(String []args) {
		AnalyseDataService analyseDataService=new AnalyseDataService();
		analyseDataService.getImageDataByName("1532575058520.jpg");
	}*/
}
