package com.xuanke.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		try {		
			if("list".equals(method)) {
				this.list(req, resp);
			}else if("add".equals(method)) {
				this.add(req, resp);
			}else if("edit".equals(method)) {
				this.findByID(req, resp);
			}else if("editsubmit".equals(method)) {
				this.editSubmit(req, resp);
			}else if("delete".equals(method)) {
				this.delete(req, resp);
			}else if("v_add".equals(method)) {
				this.v_add(req, resp);
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void v_add(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		PageInfo<Teacher> pageInfo = new PageInfo<>(1);
		pageInfo.setPageSize(1000);
		pageInfo = DAOFactory.getInstance().getTeacherDAO().list(null, pageInfo);
		req.setAttribute("teachers", pageInfo.getList());
		req.getRequestDispatcher("page/Course/add.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		try {
			DAOFactory.getInstance().getCourseDAO().delete(Integer.parseInt(id));
			resp.sendRedirect(PathUtils.getBasePath(req)+"course?method=list");
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Integer cID = Integer.parseInt(req.getParameter("cID"));
		Integer tID = Integer.parseInt(req.getParameter("tID"));
		String cName = req.getParameter("cName");
		Course course = new Course();
		course.setcID(cID);
		course.setcName(cName);
		Teacher teacher = new Teacher();
		teacher.settID(tID);
		course.setTeacher(teacher);
		DAOFactory.getInstance().getCourseDAO().update(course);
		resp.sendRedirect(PathUtils.getBasePath(req)+"course?method=list");
		
	}

	private void findByID(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		PageInfo<Teacher> pageInfo = new PageInfo<>(1);
		pageInfo.setPageSize(1000);
		pageInfo = DAOFactory.getInstance().getTeacherDAO().list(null, pageInfo);
		try {
			Course course = DAOFactory.getInstance().getCourseDAO().findById(Integer.parseInt(id));
			req.setAttribute("course", course);
			req.setAttribute("teachers", pageInfo.getList());
			req.getRequestDispatcher("page/Course/update.jsp").forward(req, resp);
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Integer pageNo = getIntParameter(request, "pageNo");
		Integer tID = getIntParameter(request, "tID");
		String tName = request.getParameter("tName");
		Integer cID = getIntParameter(request, "cID");
		String cName = request.getParameter("cName");
		String tNO = request.getParameter("tNO");
		
		Course course = new Course();
		course.setcName(cName);
		course.setcID(cID);
		
		Teacher teacher = new Teacher();
		teacher.settID(tID);
		teacher.settNO(tNO);
		teacher.settName(tName);
		
		course.setTeacher(teacher);
		
		//构造了一个pageInfo对象
		PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DAOFactory.getInstance().getCourseDAO().list(course,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("course", course);
//			List list = pageInfo.getList();
//			for(Iterator<Student> it = list.iterator() ; it.hasNext();){
//				System.out.println(it.next().getsName()+it.next().getsNO());
//			}
			request.getRequestDispatcher("page/Course/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getIntParameter(HttpServletRequest request, String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
	
	
	private void add(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
		Integer tID = Integer.parseInt(req.getParameter("tID"));
		String cName = req.getParameter("cName");
		Course course = new Course();
		course.setcName(cName);
		Teacher teacher = new Teacher();
		teacher.settID(tID);
		course.setTeacher(teacher);
		DAOFactory.getInstance().getCourseDAO().add(course);
		resp.sendRedirect(PathUtils.getBasePath(req)+"course?method=list");
	}
}
