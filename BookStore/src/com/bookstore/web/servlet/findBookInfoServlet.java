package com.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.Product;
import com.bookstore.service.ProductService;

@WebServlet("/findBookInfoServlet")
public class findBookInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProductService ProductService=new ProductService();
		Product pInfo=ProductService.findBookInfoServlet(id);
		request.setAttribute("Bookinfo",pInfo);
		request.getRequestDispatcher("/WEB-INF/jsp/product_info.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
