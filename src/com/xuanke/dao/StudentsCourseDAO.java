package com.xuanke.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.xuanke.entity.Student;
import com.xuanke.entity.StudentsCourse;
import com.xuanke.utils.PropertiesUtils;

public class StudentsCourseDAO {
	public int[] add(List<Integer> cIDArray, Integer sID) throws SQLException {
		DataSource dataSourse = PropertiesUtils.getDataSource();
		Connection connection = dataSourse.getConnection();
		connection.setAutoCommit(false);
		QueryRunner queryRunner = new QueryRunner(dataSourse);
		
		String _sql = "delete from studentscourse where sID = ?";
		queryRunner.update(connection, _sql, sID);
		
		Object[][] object = new Object[cIDArray.size()][2];
		for(int i=0; i<cIDArray.size(); i++) {
			object[i][0] = sID;
			object[i][1] = cIDArray.get(i);
		}
		String sql = "insert into studentsCourse(sID, cID) values(?, ?)";
		int[] arr = queryRunner.batch(connection, sql, object);
		connection.commit();
		return arr;
	}
	
	public void delete(Integer scID) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "delete from studentsCourse where scID = ?";
		queryRunner.update(sql, scID);
	}
	
	public void update(String[] sIDArray, String[] scoreArray, Integer cID) throws SQLException {
		DataSource dataSource = PropertiesUtils.getDataSource();
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		Object[][] object = new Object[sIDArray.length][3];
		for(int i=0; i<sIDArray.length; i++) {
			object[i][0] = Integer.parseInt(scoreArray[i]==null||scoreArray[i]==""? "0": scoreArray[i]);
			object[i][1] = cID;
			object[i][2] = Integer.parseInt(sIDArray[i]);
		}
		QueryRunner queryRunner = new QueryRunner(dataSource);
		String sql = "update studentsCourse set score = ? where cID = ? and sID = ?";
		queryRunner.batch(sql, object);
		connection.commit();
	}
	
	public List<StudentsCourse> list(StudentsCourse studentsCourse)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from studentsCourse";
		List<StudentsCourse> list = queryRunner.query(sql, new BeanListHandler<>(StudentsCourse.class));
		return list;
	}
	
	public List<StudentsCourse> listBysID(Integer sID)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select * from studentsCourse where sID = ?";
		List<StudentsCourse> list = queryRunner.query(sql, new BeanListHandler<>(StudentsCourse.class), sID);
		return list;
	}
	
	public List<Map<String, Object>> listBycID(Integer cID)throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select (select cName from course where course.cid=studentscourse.cid) as cName, "
				+ "students.sID as sID, students.sName as sName, score from students, studentsCourse "
				+ "where studentscourse.sID=students.sID and cID = ?";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler(), cID);
		return list;
	}
	
	public List<Map<String,Object>> findBysID(Integer sID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select studentscourse.cID, (select cName from course where course.cid=studentscourse.cid) as cName, score from studentsCourse where sID = ?";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler(), sID);
		return list;
	}
	
	public List<Map<String,Object>> queryRange() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cID, cName, IFNULL(A,0) A, IFNULL(B,0) B, IFNULL(C,0) C, IFNULL(D,0) D, IFNULL(E,0) E from course LEFT JOIN(" + 
				" select cid, count(*) E from studentscourse where score<60 GROUP BY cID" + 
				" )E on course.cid=E.cid LEFT JOIN(" + 
				" select cid, count(*) D from studentscourse where score>60 and score<=70 GROUP BY cID" + 
				" )D on course.cid=D.cid LEFT JOIN(" + 
				" select cid, count(*) C from studentscourse where score>70 and score<=80 GROUP BY cID" + 
				" )C on course.cid=C.cid LEFT JOIN(" + 
				" select cid, count(*) B from studentscourse where score>80 and score<=90 GROUP BY cID" + 
				" )B on course.cid=B.cid LEFT JOIN(" + 
				" select cid, count(*) A from studentscourse where score>90 and score<=100 GROUP BY cID" + 
				" )A on course.cid=A.cid";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;	
	}
	
	public List<Map<String,Object>> queryPassRate() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "SELECT A.cID, (" + 
				" SELECT cName FROM course WHERE course.cID=A.cid" + 
				" ) cName, passed, totals, ROUND(passed/totals,2)*100 pass_rate FROM(" + 
				"	SELECT cid, count(*) passed FROM studentscourse WHERE score>=60 GROUP BY cID" + 
				" ) A, (" + 
				"  SELECT cid, count(*) totals FROM studentscourse GROUP BY cID" + 
				" ) B WHERE B.cID = A.cID";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler());
		return list;	
	}
	
	public List<Map<String,Object>> queryRangeBytID(Integer tID) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "select course.cID, cName, IFNULL(A,0) A, IFNULL(B,0) B, IFNULL(C,0) C, IFNULL(D,0) D, IFNULL(E,0) E from course LEFT JOIN(" + 
				" select cid, count(*) E from studentscourse where score<60 GROUP BY cID" + 
				" )E on course.cid=E.cid LEFT JOIN(" + 
				" select cid, count(*) D from studentscourse where score>60 and score<=70 GROUP BY cID" + 
				" )D on course.cid=D.cid LEFT JOIN(" + 
				" select cid, count(*) C from studentscourse where score>70 and score<=80 GROUP BY cID" + 
				" )C on course.cid=C.cid LEFT JOIN(" + 
				" select cid, count(*) B from studentscourse where score>80 and score<=90 GROUP BY cID" + 
				" )B on course.cid=B.cid LEFT JOIN(" + 
				" select cid, count(*) A from studentscourse where score>90 and score<=100 GROUP BY cID" + 
				" )A on course.cid=A.cid WHERE tID = ?";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler(), tID);
		return list;	
	}
	
	public List<Map<String,Object>> queryPassRateBytID(Integer tID ) throws SQLException{
		QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDataSource());
		String sql = "SELECT * FROM course, (" + 
				" SELECT A.cID, passed, totals, ROUND(passed/totals,2)*100 pass_rate FROM(" + 
				"	SELECT cid, count(*) passed FROM studentscourse WHERE score>=60 GROUP BY cID" + 
				" ) A, (" + 
				"  SELECT cid, count(*) totals FROM studentscourse GROUP BY cID" + 
				" ) B WHERE B.cID = A.cID " + 
				" ) C WHERE course.cID=C.cID AND tID = ?";
		List<Map<String,Object>> list = queryRunner.query(sql, new MapListHandler(), tID);
		return list;	
	}
	
}
