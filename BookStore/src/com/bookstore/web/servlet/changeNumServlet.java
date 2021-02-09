package com.bookstore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.domain.Product;

@WebServlet("/changeNumServlet")
public class changeNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String num = request.getParameter("num");	
		HttpSession session = request.getSession();
		Map<String,Product> cart = (Map<String,Product>) session.getAttribute("cart");
		//�����Ʒ����Ϊ0����ɾ������
		if("0".equals(num)){
			cart.remove(id);
		}
		//�ж�����ҵ���id��ͬ���飬
		if(cart.containsKey(id)){
			cart.get(id).setCount(Integer.parseInt(num));			
		}	
		response.sendRedirect(request.getContextPath()+"/jumpSevlet?path=cart.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
