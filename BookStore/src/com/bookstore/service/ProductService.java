package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.ProductDao;
import com.bookstore.domain.Order;
import com.bookstore.domain.PageBean;
import com.bookstore.domain.Product;

public class ProductService {

	//创建一个Dao对象
	private ProductDao productDao = new ProductDao();
	
	public PageBean findBooksPage(int currentPage, int pageSize, String category) {
		try {
			//总计录数
			int count=productDao.count(category);
			int totalPage=(int) Math.ceil(count*0.1/pageSize);
            List<Product> products= productDao.findBooks(currentPage,pageSize,category);
			
			//把5个变量封装到PageBean中，做为返回值
			PageBean pb = new PageBean();
			pb.setProducts(products);
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			//在pageBean中添加属性，用于点击上一页或下一页时使用
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
