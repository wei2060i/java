package com.bookstore.service;

import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.OrderDao;
import com.bookstore.dao.OrderItemDao;
import com.bookstore.dao.ProductDao;
import com.bookstore.domain.Order;
import com.bookstore.exception.OrderException;
import com.bookstore.utils.ManagerThreadLocal;

public class OrderService {
	
	private OrderDao orderDao =new OrderDao();
	private OrderItemDao orderItemDao =new OrderItemDao();
	private ProductDao productDao =new ProductDao();
	
	public void addOrder(Order order) {
		try {
			ManagerThreadLocal.startTransacation();
			orderDao.addOrder(order);
			orderItemDao.addOrderItem(order);
			productDao.updateProductNum(order);
			ManagerThreadLocal.commit();
		} catch (Exception e) {	
			e.printStackTrace();
			ManagerThreadLocal.rollback();
		}
	}

	public List<Order> findOrdersByUserId(int id) {		
		try {
			return orderDao.findOrders(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 return null;
	}

	public Order findOrderByUserId(String orderid) {
		try {
			return orderDao.findOrderByUserId(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void modifyOrderState(String r6_Order) throws OrderException {
		try {
			orderDao.modifyOrderState(r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OrderException("ÐÞ¸Ä¶©µ¥Ö§¸¶×´Ì¬Ê§°Ü");
		}
	}

	public void deleteOrderItems(String orderid) {
		try {
			orderDao.deleteOrderItems(orderid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int queryOrderCountById(int id) {
		try {
			return orderDao.queryOrderCountById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
