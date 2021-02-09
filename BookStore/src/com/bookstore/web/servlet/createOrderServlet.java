package com.bookstore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bookstore.domain.Order;
import com.bookstore.domain.OrderItem;
import com.bookstore.domain.Product;
import com.bookstore.domain.User;
import com.bookstore.service.OrderService;

import java.util.Map.Entry;
import java.util.UUID;


@WebServlet("/createOrderServlet")
public class createOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//封装Order对象
		Order order=new Order();
		try {
			BeanUtils.populate(order,request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUser((User)request.getSession().getAttribute("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Product> cart=(Map<String, Product>) request.getSession().getAttribute("cart");
		//遍历购物车中的数据   添加到 OrderItem中，同时装到list集合里面
		List<OrderItem> list=new ArrayList<>();
		for(Entry<String, Product> p:cart.entrySet()) {
			OrderItem oi=new OrderItem();
			oi.setOrder(order);
			oi.setP(p.getValue());
			oi.setBuynum(p.getValue().getCount());
			list.add(oi);
		}
		order.setOrderItems(list);
		OrderService os=new OrderService();
		os.addOrder(order);		
		//支付界面
		request.setAttribute("orderid",order.getId());
		request.setAttribute("money",order.getMoney());
		request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
