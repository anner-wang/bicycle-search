package com.creation.database;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.creation.model.Image;

public class ImageDatabase {
	private DataConnection connection=new DataConnection();
	public void addImage(Image image) {
		SqlSession sqlSession=connection.getSqlSession();
		sqlSession.insert("image.add",image);
		sqlSession.commit();
		sqlSession.close();
	}
	public List findImage(String name) {
		SqlSession sqlSession=connection.getSqlSession();
		List list=sqlSession.selectList("image.find",name);
		sqlSession.close();
		return list;
	}
	public List<Image> getImage(String time) {
		SqlSession sqlSession=connection.getSqlSession();
		List<Image> list=sqlSession.selectList("image.get",time);
		sqlSession.close();
		return list;
	}
	public List<Image> getImage(){
		SqlSession sqlSession=connection.getSqlSession();
		List<Image>list=sqlSession.selectList("image.all");
		return list;
	}
	public Image getImageByFileName(String fileName) {
		SqlSession sqlSession=connection.getSqlSession();
		Image image=sqlSession.selectOne("image.fileName",fileName);
		sqlSession.close();
		return image;
	}
	public void changeImage(Image image) {
		SqlSession sqlSession=connection.getSqlSession();
		sqlSession.update("image.change",image);
		sqlSession.commit();
		sqlSession.close();
	}
	public static void main(String []args) {
		ImageDatabase imageDatabase=new ImageDatabase();
		Image image=new Image("xftx","123","123","123","1532575058520.jpg",123,"123");
		imageDatabase.changeImage(image);
	}
}
