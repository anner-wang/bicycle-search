package com.example.demo.bean.database;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.bean.User;

@Mapper
public interface UserDataBase {
	
	@Insert("INSERT INTO user(name,uploadNumber,status) VALUES (#{name},#{uploadNumber},#{status})")
	void addToUserTable(User user);
	
	@Select("SELECT * from user")
	@Results({
		@Result(property="name",column="name"),
		@Result(property="uploadNumber",column="uploadNumber"),
		@Result(property="status",column="status")
	})
	List<User>getUsers();
	
	@Select("SELECT * from user where name=#{name}")
	@Results({
		@Result(property="name",column="name"),
		@Result(property="uploadNumber",column="uploadNumber"),
		@Result(property="status",column="status")
	})
	User getUser(String name);
	
	@Update("update user set uploadNumber=#{uploadNumber} where name=#{name}")
	void updateUser(User user);
	
}
