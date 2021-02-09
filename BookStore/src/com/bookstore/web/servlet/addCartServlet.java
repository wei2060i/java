package com.bookstore.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.domain.Product;
import com.bookstore.service.ProductService;

import net.sf.json.JSONObject;


@WebServlet("/addCartServlet")
public class addCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		String id = request.getParameter("id");	
		ProductService bs = new ProductService();		
		Product b = bs.findBookInfoServlet(id);	
		//从session中的购物车取出 来
		HttpSession session = request.getSession();
		Map<String,Product> cart =(Map<String, Product>) session.getAttribute("cart");

		//如何是第一次访问，没有购物车对象，我们就创建 一个购物车对象
		if(cart==null){
			cart = new HashMap<String,Product>();			
		}
		//查看当前集合中是否存在b这本书,如果有就把数据取出来加1;
		if(cart.containsKey(b.getId())){
			cart.get(b.getId()).setCount(cart.get(b.getId()).getCount()+1);
		}else {
			b.setCount(1);
			cart.put(b.getId(),b);
		}
		
		//把cart对象放回到session作用域中
		session.setAttribute("cart", cart);
		JSONObject from = JSONObject.fromObject(cart);
		System.out.println("====="+from);
		out.print("<a href='"+request.getContextPath()+"/pageServlet'>继续购物</a>，<a href='"+request.getContextPath()+"/jumpSevlet?path=cart.jsp'>查看购物车</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
