package com.example.demo.bean;

public class UploadVisitedNumber {
	private String time;
	private int uploadNumber;
	private int visitedNumber;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUploadNumber() {
		return uploadNumber;
	}
	public void setUploadNumber(int uploadNumber) {
		this.uploadNumber = uploadNumber;
	}
	public int getVisitedNumber() {
		return visitedNumber;
	}
	public void setVisitedNumber(int visitedNumber) {
		this.visitedNumber = visitedNumber;
	}
	@Override
	public String toString() {
		return "UploadVisitedNumber [time=" + time + ", uploadNumber=" + uploadNumber + ", visitedNumber="
				+ visitedNumber + "]";
	}
}
