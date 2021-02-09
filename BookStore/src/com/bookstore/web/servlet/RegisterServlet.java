package com.bookstore.web.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bookstore.domain.User;
import com.bookstore.exception.UserException;
import com.bookstore.service.UserService;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理验证码
		String ckcode = request.getParameter("ckcode");
		String checkcode=(String) request.getSession().getAttribute("checkcode_session");
		if(!checkcode.equals(ckcode)) {
			request.setAttribute("ckcode_msg","验证码错误啊");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,response);
			return;
		}
		//获取表单数据
		User u=new User();
		try {
			//用来将一些 key-value 的值（例如 hashmap）映射到 bean 中的属性。
			BeanUtils.populate(u,request.getParameterMap());
			u.setActiveCode(UUID.randomUUID().toString());
			UserService us=new UserService();
			us.regist(u);
			//转发
			//request.getSession().setAttribute("user",u);
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request,response);
		} catch(UserException e) {
			request.setAttribute("user_msg",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request,response);
			return;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
