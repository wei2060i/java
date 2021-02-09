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
			String emailMsg="注册成功，请<a href='http://localhost:8080/product/activeServlet?activeCode="+u.getActiveCode()+"'>激活</a>后登录";
			//发送邮件
			SendJMail.sendMail(u.getEmail(), emailMsg);
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new UserException("注册失败");
		} 
	}

	public void activeUser(String activeCode) throws UserException {
		//根据激活码查用户
		try {
			User u=ud.findUserByActiveCode(activeCode);
			if(u!=null) {
				ud.activeCode(activeCode);
				return;
			}
			throw new UserException("激活失败");
		} catch (SQLException e) {		
			e.printStackTrace();
			throw new UserException("激活失败");
		}
	}

	public User login(String username, String password) throws UserException{
		User u=null;
		try {
			u= ud.findUserByUserNameAndPassword(username,password);
			if(u==null) {
				throw new UserException("用户名或密码错误");
			}
			if(u.getState()==0){
				throw new UserException("用户my激活");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("用户名或密码错误");
		}
		return u;
	}

	public User findUserById(String id) throws UserException {
		try {
			return ud.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("用户查找失败");
		}		
	}

	public void modifyUser(User user) throws UserException {
		try {
			ud.modifyUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("用户修改失败");
		}
	}
	
}
