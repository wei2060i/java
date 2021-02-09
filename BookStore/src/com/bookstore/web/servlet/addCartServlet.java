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
		//��session�еĹ��ﳵȡ�� ��
		HttpSession session = request.getSession();
		Map<String,Product> cart =(Map<String, Product>) session.getAttribute("cart");

		//����ǵ�һ�η��ʣ�û�й��ﳵ�������Ǿʹ��� һ�����ﳵ����
		if(cart==null){
			cart = new HashMap<String,Product>();			
		}
		//�鿴��ǰ�������Ƿ����b�Ȿ��,����оͰ�����ȡ������1;
		if(cart.containsKey(b.getId())){
			cart.get(b.getId()).setCount(cart.get(b.getId()).getCount()+1);
		}else {
			b.setCount(1);
			cart.put(b.getId(),b);
		}
		
		//��cart����Żص�session��������
		session.setAttribute("cart", cart);
		JSONObject from = JSONObject.fromObject(cart);
		System.out.println("====="+from);
		out.print("<a href='"+request.getContextPath()+"/pageServlet'>��������</a>��<a href='"+request.getContextPath()+"/jumpSevlet?path=cart.jsp'>�鿴���ﳵ</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
