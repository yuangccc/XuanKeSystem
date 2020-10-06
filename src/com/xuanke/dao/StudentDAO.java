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
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PropertiesUtils;

public class StudentDAO {

	
	public void add(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into students(sName, sNO, sPwd) values(?, ?, ?)";
		queryRunner.update(sql, student.getsName(), student.getsNO(), student.getsPwd());
	}
	
	public void delete(Integer sID) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from students where sID = ?";
		queryRunner.update(sql, sID);
	}
	
	public void update(Student student) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update students set sName = ?, sPwd = ?, sNO = ?  where sID = ?";
		queryRunner.update(sql, student.getsName(), student.getsPwd(), student.getsNO(), student.getsID());
	}
	
	public PageInfo<Student> list(Student student, PageInfo<Student> pageInfo)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String _sql = "";
		List<Object> _list = new ArrayList<Object>();
		if(student.getsID() != null) {
			_sql += " and SID = ?";
			_list.add(student.getsID());
		}
		if(StringUtils.isNoneBlank(student.getsName())) {
			_sql += " and SNAME like ?";
			_list.add("%"+student.getsName()+"%");
		}
		if(StringUtils.isNoneBlank(student.getsNO())) {
			_sql += " and SNO like ?";
			_list.add("%"+student.getsNO()+"%");
		}
		//_list转数组
		Object[] arr = new Object[_list.size()];
		for (int i=0;i<_list.size();i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select * from students where 1=1 "+_sql+" limit  "+(pageInfo.getPageNo()-1)*pageInfo.getPageSize()+" , "+pageInfo.getPageSize();
		List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class), arr);
		
		pageInfo.setList(list);
		pageInfo.setTotalCount(this.count(student)); 
		return pageInfo;
	}
	

	public Long count(Student student)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		List<Object> _list = new ArrayList<>();
		String _sql = "";
		if(student.getsID() != null) {
			_sql += "and sID = ?";
			_list.add(student.getsID());
		}
		if(StringUtils.isNoneBlank(student.getsName())) {
			_sql += " and sName like ?";
			_list.add("%"+student.getsName() +"%");
		}
		if(StringUtils.isNoneBlank(student.getsNO())) {
			_sql += " and sNO like ?";
			_list.add("%"+student.getsNO() +"%");
		}
		Object[] arr = new Object[_list.size()];
		for(int i=0; i<_list.size();  i++) {
			arr[i] = _list.get(i);
		}
		String sql = "select count(*) from students where 1=1 " + _sql;
		Long count = (Long)queryRunner.query(sql, new ScalarHandler<>(), arr);
		return count;
	}
	
	public Student findById(Integer sID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from students where sID = ?";
		Student student = queryRunner.query(sql, new BeanHandler<>(Student.class), sID);
		return student;
	}
	
	public Student login(String sNO, String sPwd) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from students where sNO = ? and sPwd = ?";
		Student entity = queryRunner.query(sql, new BeanHandler<>(Student.class), sNO, sPwd);
		return entity;
	}
	
	
}
