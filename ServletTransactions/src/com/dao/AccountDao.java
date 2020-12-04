package com.dao;

import com.domain.Account;

public interface AccountDao {
	/**
	 * 根据账户信息修改金额
	 * @param accout
	 */
	public void updateAccout(Account accout) throws Exception;
	
	/**
	 * 根据用户名查找账户信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByName(String name)throws Exception;
}
