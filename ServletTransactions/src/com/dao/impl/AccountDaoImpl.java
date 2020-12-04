package com.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.AccountDao;
import com.domain.Account;
import com.util.C3P0Util;
import com.util.ManagerThreadLocal;

public class AccountDaoImpl implements AccountDao {

	public void updateAccout(Account account) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),"update account set money=? where name=?",account.getMoney(),account.getName());
	}

	public Account findAccountByName(String name) throws Exception {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from account where name=?", new BeanHandler<Account>(Account.class),name);
	}

}
