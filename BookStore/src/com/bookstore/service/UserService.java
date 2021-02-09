package com.bookstore.service;

import java.sql.SQLException;

import com.bookstore.dao.UserDao;
import com.bookstore.domain.User;
import com.bookstore.exception.UserException;
import com.bookstore.utils.SendJMail;

public class UserService {
	
	private UserDao ud=new UserDao();
	
	public void regist(User u) throws UserException {
		try {
			ud.addUser(u);
			String emailMsg="ע��ɹ�����<a href='http://localhost:8080/product/activeServlet?activeCode="+u.getActiveCode()+"'>����</a>���¼";
			//�����ʼ�
			SendJMail.sendMail(u.getEmail(), emailMsg);
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new UserException("ע��ʧ��");
		} 
	}

	public void activeUser(String activeCode) throws UserException {
		//���ݼ�������û�
		try {
			User u=ud.findUserByActiveCode(activeCode);
			if(u!=null) {
				ud.activeCode(activeCode);
				return;
			}
			throw new UserException("����ʧ��");
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new UserException("����ʧ��");
		}
	}

	public User login(String username, String password) throws UserException{
		User u=null;
		try {
			u= ud.findUserByUserNameAndPassword(username,password);
			if(u==null) {
				throw new UserException("�û������������");
			}
			if(u.getState()==0){
				throw new UserException("�û�my����");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("�û������������");
		}
		return u;
	}

	public User findUserById(String id) throws UserException {
		try {
			return ud.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("�û�����ʧ��");
		}		
	}

	public void modifyUser(User user) throws UserException {
		try {
			ud.modifyUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("�û��޸�ʧ��");
		}
	}
	
}
