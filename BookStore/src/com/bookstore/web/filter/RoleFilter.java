package com.bookstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.User;

public class RoleFilter implements Filter {

	@Override
	public void destroy() {
			
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		//从session中把用户对象得到
		User user = (User) request.getSession().getAttribute("user");
		//判断当前用户权限
		if(user!=null){
			if(!"admin".equals(user.getRole())){
				response.getWriter().write("权限不足，你无法访问！");
				response.setHeader("refresh", "2;url="+request.getContextPath()+"/jumpSevlet?path=index.jsp");
				return;
			}
			//放行
			arg2.doFilter(request, response);
		}
		response.sendRedirect(request.getContextPath()+"/jumpSevlet?path=login.jsp");	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
