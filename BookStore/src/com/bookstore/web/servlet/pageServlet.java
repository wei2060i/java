package com.bookstore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.domain.PageBean;
import com.bookstore.service.ProductService;

@WebServlet("/pageServlet")
public class pageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������ť�Ĳ�ѯ����
				String category = request.getParameter("category");
				if(category==null){
					category="";
				}
				//������˵�get����,��ʱ�����ɡ�����������
				category="��ѧ";
				//��ʼ��ÿҳ��ʾ�ļ�¼��
				int pageSize = 4;			
				int currentPage = 1;//��ǰҳ
				String currPage = request.getParameter("currentPage");//����һҳ����һҳ�õ�������
				if(currPage!=null&&!"".equals(currPage)){//��һ�η�����Դʱ��currPage������null
					currentPage =Integer.parseInt(currPage);
				}				
				ProductService bs = new ProductService();
				//��ҳ��ѯ��������PageBean����
				PageBean pb = bs.findBooksPage(currentPage,pageSize,category);		
				request.setAttribute("pb", pb);
				request.getRequestDispatcher("/WEB-INF/jsp/product_list.jsp").forward(request, response);		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
