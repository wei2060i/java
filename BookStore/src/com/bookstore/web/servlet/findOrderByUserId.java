package com.bookstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.Order;
import com.bookstore.domain.User;
import com.bookstore.service.OrderService;

@WebServlet("/findOrderByUserId")
public class findOrderByUserId extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		OrderService os=new OrderService();
		List<Order> orders=os.findOrdersByUserId(user.getId());
		int orderCount=os.queryOrderCountById(user.getId());
		request.setAttribute("orders",orders);
		request.setAttribute("countOrders",orderCount);
		request.getRequestDispatcher("/WEB-INF/jsp/orderlist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
