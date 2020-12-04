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
			//�ֱ�õ�ת����ת���˻�����
			Account fromAccount = ad.findAccountByName(fromname);
			Account toAccount = ad.findAccountByName(toname);
			
			//�޸��˻����ԵĽ��
			fromAccount.setMoney(fromAccount.getMoney()-money);
			toAccount.setMoney(toAccount.getMoney()+money);
			
			//���ת�˲���
			ad.updateAccout(fromAccount);
//			int i = 10/0;
			ad.updateAccout(toAccount);			
			ManagerThreadLocal.commit();//�ύ����
		} catch (Exception e) {
			try {
				ManagerThreadLocal.rollback();//�ع�����
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}finally{
			try {
				ManagerThreadLocal.close();
			} catch (Exception e) {
				e.printStackTrace();
			}//�ر�
		}
	}

}
