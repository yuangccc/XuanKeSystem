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

@WebServlet("/sc")
public class SCServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		try {
			if("select".equals(method)) {
				this.select(req, resp);
			}else if("submit".equals(method)){
				this.submit(req, resp);
			}else if("tc".equals(method)) {
				this.teacherCourse(req, resp);
			}else if("cs".equals(method)) {
				this.course_student(req, resp);
			}else if("score".equals(method)) {
				this.score(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void score(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		Integer cID = getIntParameter(req, "cID");
		String[] sIDArray = req.getParameterValues("sID");
		String[] scoreArray = req.getParameterValues("score");
		try {
			DAOFactory.getInstance().getStudentsCourseDAO().update(sIDArray, scoreArray, cID);
			resp.sendRedirect(PathUtils.getBasePath(req)+"sc?method=cs&cID="+cID+"&msg=0");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect(PathUtils.getBasePath(req)+"sc?method=cs&cID="+cID+"&msg=1");
			e.printStackTrace();
		}
		
	}

	private void course_student(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Integer cID = getIntParameter(req, "cID");
		List<Map<String,Object>> list = DAOFactory.getInstance().getStudentsCourseDAO().listBycID(cID);
		req.setAttribute("list", list);
		req.setAttribute("cID", cID);
		req.getRequestDispatcher("page/SC/course_student.jsp").forward(req, resp);
	}

	private void teacherCourse(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Teacher teacher = (Teacher)req.getSession().getAttribute("user");
		Integer pageNo = getIntParameter(req, "pageNo");
		Course course = new Course();
		course.setTeacher(teacher);
		PageInfo<Course> pageInfo = new PageInfo<>(pageNo);
		pageInfo = DAOFactory.getInstance().getCourseDAO().list(course, pageInfo);
		
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("page/SC/teacher_course.jsp").forward(req, resp);		
	}

	private void submit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String[] cIDs = req.getParameterValues("cID");
		List<Integer> cIDArray = new ArrayList<>();
		for(String string: cIDs) {
			cIDArray.add(Integer.parseInt(string));
		}
		Student student = (Student)req.getSession().getAttribute("user");
		int[] arr = DAOFactory.getInstance().getStudentsCourseDAO().add(cIDArray, student.getsID());
		if(arr.length != 0|| arr != null) {
			resp.sendRedirect(PathUtils.getBasePath(req)+"sc?method=select&msg=0");
		}else {
			resp.sendRedirect(PathUtils.getBasePath(req)+"sc?method=select&msg=1");
		}
	}

	private void select(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		PageInfo<Course> info = new PageInfo<>(1);
		info.setPageSize(1000);
		info = DAOFactory.getInstance().getCourseDAO().list(null, info);
		
		Student student = (Student) req.getSession().getAttribute("user");
		List<StudentsCourse> list = DAOFactory.getInstance().getStudentsCourseDAO().listBysID(student.getsID());
		
		req.setAttribute("scs", list);
		req.setAttribute("courses", info.getList());
		req.getRequestDispatcher("page/SC/select.jsp").forward(req, resp);
	}
	
	public Integer getIntParameter(HttpServletRequest request, String name) {
		if(StringUtils.isNoneBlank(request.getParameter(name))) {
			return Integer.parseInt(request.getParameter(name));
		}else {
			return null;
		}
	}
}
