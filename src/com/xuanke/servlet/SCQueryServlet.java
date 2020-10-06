package com.xuanke.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import com.xuanke.dao.DAOFactory;
import com.xuanke.entity.Course;
import com.xuanke.entity.Student;
import com.xuanke.entity.StudentsCourse;
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PathUtils;

@WebServlet("/scquery")
public class SCQueryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		try {
			if("query_range".equals(method)) {
				this.query_range(req, resp);
			}else if("query_passrate".equals(method)){
				this.query_passrate(req, resp);
			}else if("query_teacher".equals(method)){
				this.query_teacher(req, resp);
			}else if("query_student".equals(method)){
				this.query_student(req, resp);
			}else if("listScoreBycID".equals(method)){
				this.listScoreBycID(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void listScoreBycID(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Integer cID = getIntParameter(req, "cID");
		List<Map<String,Object>> list = DAOFactory.getInstance().getStudentsCourseDAO().listBycID(cID);
		req.setAttribute("list", list);
		req.getRequestDispatcher("page/SC/course_score.jsp").forward(req, resp);
	}

	private void query_student(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Student student = (Student) req.getSession().getAttribute("user");
		Integer sID = student.getsID();
		List<Map<String,Object>> list = DAOFactory.getInstance().getStudentsCourseDAO().findBysID(sID);
		req.setAttribute("list", list);
		req.getRequestDispatcher("page/SC/query_student.jsp").forward(req, resp);
	}

	private void query_teacher(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher teacher = (Teacher) req.getSession().getAttribute("user");
		Integer tID = teacher.gettID();
		List<Map<String,Object>> list1 = DAOFactory.getInstance().getStudentsCourseDAO().queryRangeBytID(tID);
		req.setAttribute("list1", list1);
		List<Map<String,Object>> list2 = DAOFactory.getInstance().getStudentsCourseDAO().queryPassRateBytID(tID);
		req.setAttribute("list2", list2);
		req.getRequestDispatcher("page/SC/query_teacher.jsp").forward(req, resp);
	}

	private void query_passrate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = DAOFactory.getInstance().getStudentsCourseDAO().queryPassRate();
		req.setAttribute("list", list);
		req.getRequestDispatcher("page/SC/query_passrate.jsp").forward(req, resp);
	}

	public void query_range(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = DAOFactory.getInstance().getStudentsCourseDAO().queryRange();
		req.setAttribute("list", list);
		req.getRequestDispatcher("page/SC/query_range.jsp").forward(req, resp);
	}

	public Integer getIntParameter(HttpServletRequest request, String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
}
