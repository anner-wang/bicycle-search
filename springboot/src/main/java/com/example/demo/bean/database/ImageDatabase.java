package com.example.demo.bean.database;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.bean.Image;

@Mapper
public interface ImageDatabase {
	@Insert("insert into image (name,time,longitude,latitude,fileName,number,ps) value (#{name},#{time},#{longitude},#{latitude},#{fileName},#{number},#{ps})")
	void addImage(Image image);
	
	@Select("select * from image ")
	@Results({
		@Result(property = "name",  column = "name"),
		@Result(property = "time", column = "time"),
		@Result(property = "longitude", column = "longitude"),
		@Result(property = "latitude", column = "latitude"),
		@Result(property = "fileName", column = "fileName"),
		@Result(property = "number", column = "number")
	})
	List<Image> getImages();
	
	@Select("select * from image where name=#{name}")
	@Results({
		@Result(property = "name",  column = "name"),
		@Result(property = "time", column = "time"),
		@Result(property = "longitude", column = "longitude"),
		@Result(property = "latitude", column = "latitude"),
		@Result(property = "fileName", column = "fileName"),
		@Result(property = "number", column = "number")
	})
	List<Image> findImage(String name) ;
	
	@Select("select * from image where time like concat(concat('%',#{time}),'%')")
	@Results({
		@Result(property = "name",  column = "name"),
		@Result(property = "time", column = "time"),
		@Result(property = "longitude", column = "longitude"),
		@Result(property = "latitude", column = "latitude"),
		@Result(property = "fileName", column = "fileName"),
		@Result(property = "number", column = "number")
	})
	List<Image> getImage(String time);

	@Select("select * from image where fileName =#{fileName}")
	@Results({
		@Result(property = "name",  column = "name"),
		@Result(property = "time", column = "time"),
		@Result(property = "longitude", column = "longitude"),
		@Result(property = "latitude", column = "latitude"),
		@Result(property = "fileName", column = "fileName"),
		@Result(property = "number", column = "number")
	})
	Image getImageByFileName(String fileName);
	
	@Update("update image set  number=#{number} where fileName=#{fileName}")
	@Results({
		@Result(property = "name",  column = "name"),
		@Result(property = "time", column = "time"),
		@Result(property = "longitude", column = "longitude"),
		@Result(property = "latitude", column = "latitude"),
		@Result(property = "fileName", column = "fileName"),
		@Result(property = "number", column = "number")
	})
	void changeImage(Image image);
	
	
	
	
	
	
}
