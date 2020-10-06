package com.xuanke.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import com.xuanke.dao.DAOFactory;
import com.xuanke.entity.Admin;
import com.xuanke.entity.Student;
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PathUtils;

@WebServlet("/login")
public class loginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("userName");
		String password = req.getParameter("password");
		String type = req.getParameter("type");
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(type)) {
			req.setAttribute("error", "录入信息不能为空!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			return;
		}
		HttpSession session = req.getSession();
		
		
		if(StringUtils.isNotBlank(type)) {
			try {
				if("0".equals(type)) {
					Student student = DAOFactory.getInstance().getStudentDAO().login(username, password);				
					if(student != null) {
						session.setAttribute("user", student);
						session.setAttribute("type", type);
						resp.sendRedirect(PathUtils.getBasePath(req)+"index.jsp");
					}else {
						req.setAttribute("error", "用户名或密码错误");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}else if("1".equals(type)) {
					Teacher teacher = DAOFactory.getInstance().getTeacherDAO().login(username, password);
					if(teacher != null) {
						session.setAttribute("user", teacher);
						session.setAttribute("type", type);
						resp.sendRedirect(PathUtils.getBasePath(req)+"index.jsp");
					}else {
						req.setAttribute("error", "用户名或密码错误");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}else if("2".equals(type)) {
					Admin admin = DAOFactory.getInstance().getAdminDAO().login(username, password);
					if(admin != null) {
						session.setAttribute("user", admin);
						session.setAttribute("type", type);
						resp.sendRedirect(PathUtils.getBasePath(req)+"index.jsp");
					}else {
						req.setAttribute("error", "用户名或密码错误");
						req.getRequestDispatcher("login.jsp").forward(req, resp);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
