package com.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.User;
import com.bookstore.exception.UserException;
import com.bookstore.service.UserService;

@WebServlet("/findUserById")
public class findUserById extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		UserService us=new UserService();
		try {
			User user=us.findUserById(id);
			request.setAttribute("user",user);
			request.getRequestDispatcher("/WEB-INF/jsp/modifyuserinfo.jsp").forward(request, response);
		} catch (UserException e) {
			response.sendRedirect(request.getContextPath()+"/jumpSevlet?path=login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
