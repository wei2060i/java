package com.dao;

import com.domain.Account;

public interface AccountDao {
	/**
	 * �����˻���Ϣ�޸Ľ��
	 * @param accout
	 */
	public void updateAccout(Account accout) throws Exception;
	
	/**
	 * �����û��������˻���Ϣ
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Account findAccountByName(String name)throws Exception;
}
