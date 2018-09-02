package com.creation.model;

public class User {
	private String name;
	private int uploadNumber;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUploadNumber() {
		return uploadNumber;
	}
	public User(String name, int uploadNumber, String status) {
		super();
		this.name = name;
		this.uploadNumber = uploadNumber;
		this.status = status;
	}
	public User() {}
	public void setUploadNumber(int uploadNumber) {
		this.uploadNumber = uploadNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", uploadNumber=" + uploadNumber + ", status=" + status + "]";
	}
	
}
