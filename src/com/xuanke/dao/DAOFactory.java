package com.xuanke.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xuanke.entity.Admin;

public class DAOFactory {

	private static DAOFactory dao = new DAOFactory();
	private DAOFactory(){}
	
	private Map<String, Object> map = new ConcurrentHashMap<>();
	
	public static DAOFactory getInstance() {
		return dao;
	}
	
	public AdminDAO getAdminDAO() {
		AdminDAO dao = (AdminDAO)map.get("AdminDAO");
		if(dao != null) {
			return dao;
		}else {
			dao = new AdminDAO();
			map.put("AdminDAO", dao);
		}
		return dao;
	}
	
	public StudentDAO getStudentDAO() {
		StudentDAO dao = (StudentDAO)map.get("StudentDAO");
		if(dao != null) {
			return dao;
		}else {
			dao = new StudentDAO();
			map.put("StudentDAO", dao);
		}
		return dao;
	}
	public TeacherDAO getTeacherDAO() {
		TeacherDAO dao = (TeacherDAO)map.get("TeacherDAO");
		if(dao != null) {
			return dao;
		}else {
			dao = new TeacherDAO();
			map.put("TeacherDAO", dao);
		}
		return dao;
	}
	public CourseDAO getCourseDAO() {
		CourseDAO dao = (CourseDAO)map.get("CourseDAO");
		if(dao != null) {
			return dao;
		}else {
			dao = new CourseDAO();
			map.put("CourseDAO", dao);
		}
		return dao;
	}
	public StudentsCourseDAO getStudentsCourseDAO() {
		StudentsCourseDAO dao = (StudentsCourseDAO)map.get("StudentsCourseDAODAO");
		if(dao != null) {
			return dao;
		}else {
			dao = new StudentsCourseDAO();
			map.put("StudentsCourseDAO", dao);
		}
		return dao;
	}

}