package com.creation.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataConnection {
	public SqlSession getSqlSession() {
		String resource="sqlConfig.xml";
		SqlSession sqlSession=null;
		try {
			SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
			sqlSession=sqlSessionFactory.openSession();
		}catch(Exception e) {
		//	console.writeToDesktop("ªÒ»°sqlSession“Ï≥£:"+e.getMessage());
		}
		return sqlSession;
	}
}
