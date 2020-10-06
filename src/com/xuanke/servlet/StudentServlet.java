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
import com.xuanke.entity.Student;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PathUtils;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

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
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		try {
			DAOFactory.getInstance().getStudentDAO().delete(Integer.parseInt(id));
			resp.sendRedirect(PathUtils.getBasePath(req)+"student?method=list");
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Integer sID = Integer.parseInt(req.getParameter("sID"));
		String sNO = req.getParameter("sNO");
		String sName = req.getParameter("sName");
		String sPwd = req.getParameter("sPwd");
		Student student = new Student();
		student.setsPwd(sPwd);
		student.setsName(sName);
		student.setsNO(sNO);
		student.setsID(sID);
		DAOFactory.getInstance().getStudentDAO().update(student);
		resp.sendRedirect(PathUtils.getBasePath(req)+"student?method=list");
		
	}

	private void findByID(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		try {
			Student student = DAOFactory.getInstance().getStudentDAO().findById(Integer.parseInt(id));
			req.setAttribute("student", student);
			req.getRequestDispatcher("page/Student/update.jsp").forward(req, resp);
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Integer pageNo = getIntParameter(request, "pageNo");
		Integer sID = getIntParameter(request, "sID");
		String sName = request.getParameter("sName");
		String sNO = request.getParameter("sNO");
		
		Student student = new Student();
		student.setsID(sID);
		student.setsName(sName);
		student.setsNO(sNO);
		
		//构造了一个pageInfo对象
		PageInfo<Student> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DAOFactory.getInstance().getStudentDAO().list(student,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("student", student);
//			List list = pageInfo.getList();
//			for(Iterator<Student> it = list.iterator() ; it.hasNext();){
//				System.out.println(it.next().getsName()+it.next().getsNO());
//			}
			
			
			request.getRequestDispatcher("page/Student/list.jsp").forward(request, response);
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
		String sNO = req.getParameter("sNO");
		String sName = req.getParameter("sName");
		String sPwd = req.getParameter("sPwd");
		Student student = new Student();
		student.setsName(sName);
		student.setsNO(sNO);
		student.setsPwd(sPwd);
		DAOFactory.getInstance().getStudentDAO().add(student);
		resp.sendRedirect(PathUtils.getBasePath(req)+"student?method=list");
	}
}
