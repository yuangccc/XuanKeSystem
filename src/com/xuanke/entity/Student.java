package com.xuanke.entity;

public class Student {
	
	private Integer sID;
	private String sName;
	private String sNO;
	private String sPwd;
	
	private Integer score;
	private String cName;
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getsID() {
		return sID;
	}
	public void setsID(Integer sID) {
		this.sID = sID;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsNO() {
		return sNO;
	}
	public void setsNO(String sNO) {
		this.sNO = sNO;
	}
	public String getsPwd() {
		return sPwd;
	}
	public void setsPwd(String sPwd) {
		this.sPwd = sPwd;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}

	
}
