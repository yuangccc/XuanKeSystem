package com.xuanke.entity;

public class Course {

	private Integer cID;
	private String cName;
	private Teacher teacher;
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Integer getcID() {
		return cID;
	}
	public void setcID(Integer cID) {
		this.cID = cID;
	}

	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	
}
