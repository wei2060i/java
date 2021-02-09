package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.domain.Order;
import com.bookstore.domain.OrderItem;
import com.bookstore.domain.Product;
import com.bookstore.utils.C3P0Util;
import com.bookstore.utils.ManagerThreadLocal;

public class OrderDao {
	
	//��Ӷ���
	public void addOrder(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)", order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverPhone(),order
						.getPaystate(), order.getOrdertime(), order.getUser()
						.getId());
	}
	//��ѯ����	
	public List<Order> findOrders(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from orders where user_id=?",new BeanListHandler<Order>(Order.class),id);
	}
	//���� ��ϸ��Ϣ
	public Order findOrderByUserId(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//�õ�һ������
		Order order = qr.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),orderid);
		List<OrderItem>  orderItems = qr.query("SELECT * FROM orderitem o,products p WHERE p.id=o.product_id AND order_id=?", new ResultSetHandler<List<OrderItem>>(){
			@Override
			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				while(rs.next()){				
					//��װOrderItem����
					OrderItem oi = new OrderItem();
					oi.setBuynum(rs.getInt("buynum"));
					//��װProduct����
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					//��ÿ��p�����װ��OrderItem������
					oi.setP(p);
					//��ÿ��OrderItem�����װ��������
					orderItems.add(oi);
				}
				return orderItems;
			}
			
		},orderid);
		//�����еĶ�����orderItems��ӵ���������Order��
		order.setOrderItems(orderItems);
		return order;
	}
	public void deleteOrderItems(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("delete from orderitem where order_id=?",orderid);
		qr.update("delete from orders where id=?",orderid);
	}
	public int queryOrderCountById(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Long l=(Long) qr.query("select count(*) from orders where user_id=?",new ScalarHandler(1),id);
		return l.intValue();
	}
	//�޸Ķ��� ֧��״̬
	public void modifyOrderState(String r6_Order) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update orders set paystate=1 where id=?",r6_Order);
	}

}
