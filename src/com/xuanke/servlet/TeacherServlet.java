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
import com.xuanke.entity.Teacher;
import com.xuanke.utils.PageInfo;
import com.xuanke.utils.PathUtils;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

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
			DAOFactory.getInstance().getTeacherDAO().delete(Integer.parseInt(id));
			resp.sendRedirect(PathUtils.getBasePath(req)+"teacher?method=list");
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void editSubmit(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Integer tID = Integer.parseInt(req.getParameter("tID"));
		String tNO = req.getParameter("tNO");
		String tName = req.getParameter("tName");
		String tPwd = req.getParameter("tPwd");
		Teacher teacher = new Teacher();
		teacher.settID(tID);
		teacher.settName(tName);
		teacher.settNO(tNO);
		teacher.settPwd(tPwd);
		DAOFactory.getInstance().getTeacherDAO().update(teacher);
		resp.sendRedirect(PathUtils.getBasePath(req)+"teacher?method=list");
		
	}

	private void findByID(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		try {
			Teacher teacher = DAOFactory.getInstance().getTeacherDAO().findById(Integer.parseInt(id));
			req.setAttribute("teacher", teacher);
			req.getRequestDispatcher("page/Teacher/update.jsp").forward(req, resp);
		} catch (NumberFormatException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Integer pageNo = getIntParameter(request, "pageNo");
		Integer tID = getIntParameter(request, "tID");
		String tName = request.getParameter("tName");
		String tNO = request.getParameter("tNO");
		
		Teacher teacher = new Teacher();
		teacher.settID(tID);
		teacher.settName(tName);
		teacher.settNO(tNO);
		
		//构造了一个pageInfo对象
		PageInfo<Teacher> pageInfo = new PageInfo<>(pageNo);
		try {
			pageInfo = DAOFactory.getInstance().getTeacherDAO().list(teacher,pageInfo);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			request.setAttribute("pageInfo", pageInfo);
			//回写到页面
			request.setAttribute("teacher", teacher);
//			List list = pageInfo.getList();
//			for(Iterator<Student> it = list.iterator() ; it.hasNext();){
//				System.out.println(it.next().getsName()+it.next().getsNO());
//			}
			request.getRequestDispatcher("page/Teacher/list.jsp").forward(request, response);
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
		String tNO = req.getParameter("tNO");
		String tName = req.getParameter("tName");
		String tPwd = req.getParameter("tPwd");
		Teacher teacher = new Teacher();
		teacher.settName(tName);
		teacher.settNO(tNO);
		teacher.settPwd(tPwd);
		DAOFactory.getInstance().getTeacherDAO().add(teacher);
		resp.sendRedirect(PathUtils.getBasePath(req)+"teacher?method=list");
	}
}
