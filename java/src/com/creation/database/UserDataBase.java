package com.creation.database;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.creation.model.User;

public class UserDataBase {
	private DataConnection connection=new DataConnection();
	public void addToUserTable(User user) {
		SqlSession sqlSession=connection.getSqlSession();
		sqlSession.insert("user.add", user);
		sqlSession.commit();
		sqlSession.close();
	}
	public boolean isUserExist(User user) {
		SqlSession session=connection.getSqlSession();
		if(session.selectOne("user.find",user)==null) {
			return false;
		}
		session.close();
		return true;
	}
	public List getUser() {
		SqlSession sqlSession=connection.getSqlSession();
		return sqlSession.selectList("user.getUsers");
	}
	public User getUser(String name) {
		SqlSession sqlSession=connection.getSqlSession();
		User user=sqlSession.selectOne("user.get",name);
		sqlSession.close();
		return user;
	}
	public void updateUser(User user) {
		SqlSession sqlSession=connection.getSqlSession();
		user.setUploadNumber(user.getUploadNumber()+1);
		sqlSession.update("user.update",user);
		sqlSession.commit();
		sqlSession.close();
	}
}
