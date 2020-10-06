package com.xuanke.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.xuanke.entity.Student;
import com.xuanke.entity.Teacher;
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PropertiesUtils;

public class TeacherDAO {
	public void add(Teacher teacher) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into teachers(tName, tNO, tPwd) values(?, ?, ?)";
		queryRunner.update(sql, teacher.gettName(), teacher.gettNO(), teacher.gettPwd());
	}
	
	public void delete(Integer tID) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from teachers where tID = ?";
		queryRunner.update(sql, tID);
	}
	
	public void update(Teacher teacher) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update teachers set tName = ?, tPwd = ?, tNO = ? where tID = ?";
		queryRunner.update(sql, teacher.gettName(), teacher.gettPwd(), teacher.gettNO(), teacher.gettID());
	}
	
	public PageInfo<Teacher> list(Teacher teacher, PageInfo<Teacher> pageInfo)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(teacher != null && teacher.gettID() != null) {
			_sql += " and TID = ?";
			_list.add(teacher.gettID());
		}
		if(teacher != null && StringUtils.isNoneBlank(teacher.gettName())) {
			_sql += " and TNAME like ?";
			_list.add("%"+teacher.gettName()+"%");
		}
		if(teacher != null && StringUtils.isNoneBlank(teacher.gettNO())) {
			_sql += " and TNO like ?";
			_list.add("%"+teacher.gettNO()+"%");
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select * from teachers where 1=1 "+_sql+" limit  "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
		List<Teacher> list = queryRunner.query(sql, new BeanListHandler<>(Teacher.class), arr);
		
		pageInfo.setList(list);
		pageInfo.setTotalCount(this.count(teacher)); 
		return pageInfo;
	}
	
	public Long count(Teacher teacher)throws SQLException{		
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(teacher != null && teacher.gettID() != null) {
			_sql += " and TID = ?";
			_list.add(teacher.gettID());
		}
		if(teacher != null && StringUtils.isNoneBlank(teacher.gettName())) {
			_sql += " and TNAME like ?";
			_list.add("%"+teacher.gettName()+"%");
		}
		if(teacher != null && StringUtils.isNoneBlank(teacher.gettNO())) {
			_sql += " and TNO like ?";
			_list.add("%"+teacher.gettNO()+"%");
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
			String sql = "select count(*) from teachers where 1=1 " + _sql;
			Long count = queryRunner.query(sql, new ScalarHandler<>(), arr);
			return count;
	}
	
	public Teacher findById(Integer tID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from teachers where tID = ?";
		Teacher teacher = queryRunner.query(sql, new BeanHandler<>(Teacher.class), tID);
		return teacher;
	}
	
	public Teacher login(String tNO, String tPwd) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from teachers where tNO = ? and tPwd = ?";
		Teacher entity = queryRunner.query(sql, new BeanHandler<>(Teacher.class), tNO, tPwd);
		return entity;
	}
}
