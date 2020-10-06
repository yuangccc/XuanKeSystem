package com.xuanke.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.xuanke.entity.Course;
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PropertiesUtils;

public class CourseDAO {
	public void add(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into course(cName, tID) values(?, ?)";
		queryRunner.update(sql, course.getcName(), course.getTeacher().gettID());
	}
	
	public void delete(Integer cID) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from course where cID = ?";
		queryRunner.update(sql, cID);
	}
	
	public void update(Course course) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update course set cName = ?, tID = ? where cID = ?";
		queryRunner.update(sql, course.getcName(), course.getTeacher().gettID(), course.getcID());
	}
	
	
	
	public PageInfo<Course> list(Course course, PageInfo<Course> pageInfo)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(course != null && course.getcID() != null) {
			_sql += " and CID = ?";
			_list.add(course.getcID());
		}
		if(course != null && StringUtils.isNoneBlank(course.getcName())) {
			_sql += " and CNAME like ?";
			_list.add("%"+course.getcName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().gettName())) {
			_sql += " and TNAME like ?";
			_list.add("%"+course.getTeacher().gettName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().gettNO())) {
			_sql += " and TNO like ?";
			_list.add("%"+course.getTeacher().gettNO()+"%");
		}
		if(course != null && course.getTeacher().gettID() != null) {
			_sql += " and course.tid  = ?";
			_list.add(course.getTeacher().gettID());
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select course.*, teachers.tName, teachers.tNO from course, teachers where course.tid = teachers.tid "+_sql+" limit  "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
		List<Map<String,Object>> maplist = queryRunner.query(sql, new MapListHandler(), arr);
		List<Course> list = new ArrayList<>();
		
		for(Map map: maplist) {
			Course entity = new Course();
			entity.setcID(Integer.parseInt(map.get("cID")+""));
			entity.setcName(map.get("cName")+"");
			Teacher teacher = new Teacher();
			teacher.settID(Integer.parseInt(map.get("tID")+""));
			teacher.settName(map.get("tName")+"");
			teacher.settNO(map.get("tNO")+"");
			entity.setTeacher(teacher);
			list.add(entity);
		}
		pageInfo.setList(list);
		pageInfo.setTotalCount(this.count(course)); 
		return pageInfo;
	}
	
	public Long count(Course course)throws SQLException{		
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(course != null && course.getcID() != null) {
			_sql += " and CID = ?";
			_list.add(course.getcID());
		}
		if(course != null && StringUtils.isNoneBlank(course.getcName())) {
			_sql += " and CNAME like ?";
			_list.add("%"+course.getcName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().gettName())) {
			_sql += " and TNAME like ?";
			_list.add("%"+course.getTeacher().gettName()+"%");
		}
		if(course != null && StringUtils.isNoneBlank(course.getTeacher().gettNO())) {
			_sql += " and TNO like ?";
			_list.add("%"+course.getTeacher().gettNO()+"%");
		}
		if(course != null && course.getTeacher().gettID() != null) {
			_sql += " and course.tid  = ?";
			_list.add(course.getTeacher().gettID());
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select count(*) from course, teachers where course.tid = teachers.tid "+_sql;
		Long count = queryRunner.query(sql, new ScalarHandler<>(), arr);
		return count;
	}
	
	
	
	
	public Course findById(Integer cID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from course where cID = ?";
		Map<String, Object> map = queryRunner.query(sql, new MapHandler(), cID);
		Course course = new Course();
		course.setcID(Integer.parseInt(map.get("cID")+""));
		course.setcName(map.get("cName")+"");
		Integer tID = Integer.parseInt(map.get("tID")+"");
		Teacher teacher = DAOFactory.getInstance().getTeacherDAO().findById(tID);
		course.setTeacher(teacher);
		return course;
	}
}
