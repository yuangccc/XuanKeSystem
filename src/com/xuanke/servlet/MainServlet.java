package com.xuanke.servlet;

import java.io.IOException;
import java.sql.SQLException;
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
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PathUtils;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String, Object>> list1;
		try {
			list1 = DAOFactory.getInstance().getStudentsCourseDAO().queryPassRate();
			req.setAttribute("list1", list1);
			List<Map<String,Object>> list2 = DAOFactory.getInstance().getStudentsCourseDAO().queryRange();
			req.setAttribute("list2", list2);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
