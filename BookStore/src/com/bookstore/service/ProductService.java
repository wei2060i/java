package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.ProductDao;
import com.bookstore.domain.Order;
import com.bookstore.domain.PageBean;
import com.bookstore.domain.Product;

public class ProductService {

	//����һ��Dao����
	private ProductDao productDao = new ProductDao();
	
	public PageBean findBooksPage(int currentPage, int pageSize, String category) {
		try {
			//�ܼ�¼��
			int count=productDao.count(category);
			int totalPage=(int) Math.ceil(count*0.1/pageSize);
            List<Product> products= productDao.findBooks(currentPage,pageSize,category);
			
			//��5��������װ��PageBean�У���Ϊ����ֵ
			PageBean pb = new PageBean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			//��pageBean��������ԣ����ڵ����һҳ����һҳʱʹ��
			pb.setCategory(category);
			return pb;		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product findBookInfoServlet(String id) {
		try {
			return productDao.findBookInfoServlet(id);
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	public void addOrder(Order order) {
		
	}

}
