package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.domain.Order;
import com.bookstore.domain.OrderItem;
import com.bookstore.domain.Product;
import com.bookstore.service.ProductService;
import com.bookstore.utils.C3P0Util;
import com.bookstore.utils.ManagerThreadLocal;

public class ProductDao {
	
	//修改产品数量
	public void updateProductNum(Order order) throws SQLException {
		List<OrderItem> orderItems = order.getOrderItems();
		QueryRunner qr = new QueryRunner();	
		Object[][] params = new Object[orderItems.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{orderItems.get(i).getBuynum(),orderItems.get(i).getP().getId()};
		}
		qr.batch(ManagerThreadLocal.getConnection(),"UPDATE products SET pnum=pnum-? WHERE id=?", params);
	}
	public int count(String category) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="select count(*) from products";
		//如果category不是空，就把条件加上
		if(!"".equals(category)){
			sql+=" where category='"+category+"'";
		}
		long l =  (Long)qr.query(sql, new ScalarHandler(1));
		return (int)l;
	}

	public List<Product> findBooks(int currentPage, int pageSize, String category) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql="select * from products where 1=1";
		//条件添加 
	    List<Object> list=new ArrayList<>();
		if(!"".equals(category)) {
			sql=sql+" and category=?";
			list.add(category);
		}
		sql=sql+" limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);	
		List<Product> query =qr.query(sql,new BeanListHandler<Product>(Product.class),list.toArray());
		return query;
	}

	public Product findBookInfoServlet(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from products where id=?",new BeanHandler<Product>(Product.class),id);
	}

}
