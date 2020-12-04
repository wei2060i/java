package com.dbutil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.util.C3P0Util;

public class TestResultSetHandler {
	@Test//ArrayHandler:�ʺ�ȡ1����¼���Ѹ�����¼��ÿ��ֵ��װ��һ��������Object[]
	public void tese1() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[] arr  = qr.query("select * from users", new ArrayHandler());
		
		for (Object o : arr) {
			System.out.println(o);
		}
	}
	
	@Test//ArrayListHandler:�ʺ�ȡ������¼����ÿ����¼��ÿ��ֵ��װ��һ��������Object[]���������װ��һ��List��
	public void tese2() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Object[]> query = qr.query("select * from users", new ArrayListHandler());
		
		for (Object[] os : query) {
			for (Object o : os) {
				System.out.println(o);
			}
			System.out.println("--------------");
		}
	}
	
	@Test //ColumnListHandler:ȡĳһ�е����ݡ���װ��List�С�
	public void tese3() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Object> list = qr.query("select username,password from users", new ColumnListHandler(1));
		
		for (Object o : list) {
			System.out.println(o);
		}
	}
	
	@Test //KeyedHandler:ȡ������¼��ÿһ����¼��װ��һ��Map�У��ٰ����Map��װ������һ��Map�У�keyΪָ�����ֶ�ֵ��
	public void tese4() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//��Map��key�Ǳ��е�ĳ�����ݣ�СMap�е�key�Ǳ�����������Դ�Map��key��Object���ͣ�СMap��key��String����
		Map<Object,Map<String,Object>> map = qr.query("select * from users", new KeyedHandler(1));
		
		for (Map.Entry<Object, Map<String,Object>> m : map.entrySet()) {
			System.out.println(m.getKey());//��Map��keyֵ����id�е�ֵ
			for (Map.Entry<String, Object> mm : m.getValue().entrySet()) {
				System.out.println(mm.getKey()+"\t"+mm.getValue());//ȡ��СMap�е���������ֵ
			}
			System.out.println("---------------------");
		}
		
	}
	
	
	@Test//MapHandler:�ʺ�ȡ1����¼���ѵ�ǰ��¼����������ֵ�ŵ�һ��Map��
	public void tese5() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Map<String,Object> map = qr.query("select * from users where id=?", new MapHandler(),20);
		
		for (Map.Entry<String, Object> m : map.entrySet()) {
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
		
	}
	
	
	@Test//MapListHandler:�ʺ�ȡ������¼����ÿ����¼��װ��һ��Map�У��ٰ�Map��װ��List��
	public void tese6() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<Map<String,Object>> list = qr.query("select * from users", new MapListHandler());
		
		for (Map<String, Object> map : list) {
			for (Map.Entry<String, Object> m : map.entrySet()) {
				System.out.println(m.getKey()+"\t"+m.getValue());
			}
			System.out.println("---------------");
		}
	}
	
	@Test //ScalarHandler:�ʺ�ȡ���е�������
	public void tese7() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object o = qr.query("select count(*) from users", new ScalarHandler(1));
		System.out.println(o.getClass().getName());
	}
	
	@Test //BeanHandler:�ʺ�ȡ���� ����
	public void tese8() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		User user = qr.query("select * from users where id=?", new BeanHandler<User>(User.class),1);
		System.out.println(user);
	}
	
	
	@Test //BeanListHandler 
	public void tese9() throws SQLException{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List<User> list = qr.query("select * from users where id=?", new BeanListHandler<User>(User.class),1);
		
		System.out.println(list.size());
	}
}
