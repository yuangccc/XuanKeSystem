package com.xuanke.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns= {"/*"},initParams= {
		@WebInitParam(name="exclude",value="/list.jsp,/login.jsp,/login,/add.jsp,noPrivilige.jsp,.css,.png,.jpg,.js")
}
)
public class PermissionFilter implements Filter {

	public static String excludeString;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Object user = request.getSession().getAttribute("user");
		String uri = request.getRequestURI();
		if(isExist(uri) || uri.equals(request.getContextPath()+"/")) {
			chain.doFilter(request, response);
		}else{
			if(user != null) {
				chain.doFilter(request, response);
			}else {
				response.sendRedirect("noPrivilige.jsp");
			}
		}
	}
	
	public void init(FilterConfig filterConfig) {
		excludeString = filterConfig.getInitParameter("exclude");
	}
	
	public static boolean isExist(String uri) {
		String[] arr =excludeString.split(",");
		boolean flag = false;
		for(String string : arr) {
			if(uri.endsWith(string)) {
				flag = true;
			}
		}
		return flag;
	}
	
	public void destroy() {
		
	}
}
