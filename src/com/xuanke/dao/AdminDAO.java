package com.xuanke.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xuanke.entity.Admin;
import com.xuanke.utils.PropertiesUtils;

public class AdminDAO {

	public void add(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "insert into admin(aNO, aPwd, aName) values(?, ?, ?)";
		queryRunner.update(sql, admin.getaNO(), admin.getaPwd(), admin.getaName());
	}
	
	public void delete(Integer aID) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from admin where aID = ?";
		queryRunner.update(sql, aID);
	}
	
	public void update(Admin admin) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "update admin set aNO = ?, aPwd = ?, aName = ? where aID = ?";
		queryRunner.update(sql, admin.getaNO(), admin.getaPwd(), admin.getaName(), admin.getaID());
	}
	
	public List<Admin> list(Admin admin)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin";
		List<Admin> list = queryRunner.query(sql, new BeanListHandler<>(Admin.class));
		return list;
	}
	
	public Admin findById(Integer aID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where aID = ?";
		Admin admin = queryRunner.query(sql, new BeanHandler<>(Admin.class), aID);
		return admin;
	}

	public Admin login(String aNO, String aPwd) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from admin where aNO = ? and aPwd = ?";
		Admin entity = queryRunner.query(sql, new BeanHandler<>(Admin.class), aNO, aPwd);
		return entity;
	}
	
	
}
