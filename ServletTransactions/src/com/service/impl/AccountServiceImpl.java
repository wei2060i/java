package com.service.impl;

import com.dao.AccountDao;
import com.dao.impl.AccountDaoImpl;
import com.domain.Account;
import com.service.AccountService;
import com.util.C3P0Util;
import com.util.ManagerThreadLocal;

public class AccountServiceImpl implements AccountService {

	public void transfer(String fromname, String toname, double money) {
	//	ad.updateAccount(fromname, toname, money);
		AccountDao ad = new AccountDaoImpl();
		try {
			ManagerThreadLocal.startTransacation();//begin
			//分别得到转出和转入账户对象
			Account fromAccount = ad.findAccountByName(fromname);
			Account toAccount = ad.findAccountByName(toname);
			
			//修改账户各自的金额
			fromAccount.setMoney(fromAccount.getMoney()-money);
			toAccount.setMoney(toAccount.getMoney()+money);
			
			//完成转账操作
			ad.updateAccout(fromAccount);
//			int i = 10/0;
			ad.updateAccout(toAccount);			
			ManagerThreadLocal.commit();//提交事务
		} catch (Exception e) {
			try {
				ManagerThreadLocal.rollback();//回滚事务
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}finally{
			try {
				ManagerThreadLocal.close();
			} catch (Exception e) {
				e.printStackTrace();
			}//关闭
		}
	}

}
