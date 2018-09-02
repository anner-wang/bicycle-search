package com.creation.model;

public class Image {
	private String name;
	private String time;
	private String longitude;
	private String latitude;
	private String fileName;
	private int number;
	private String ps;
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return "Image [name=" + name + ", time=" + time + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", fileName=" + fileName + ", number=" + number + ", ps=" + ps + "]";
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image(String name, String time, String longitude, String latitude, String fileName, int number, String ps) {
		super();
		this.name = name;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
		this.fileName = fileName;
		this.number = number;
		this.ps = ps;
	}
	public Image() {}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	
}
