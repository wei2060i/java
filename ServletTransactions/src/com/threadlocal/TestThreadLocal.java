package com.threadlocal;

public class TestThreadLocal {
	public static void main(String[] args) {
		ThreadLocal tl = new ThreadLocal();
		tl.set("p");
		MyThread mt = new MyThread(tl);
		/*
		   * �̲߳�һ��  
		 */
		mt.start();//��� nullaaaaaaaaaaaa
		System.out.println(tl.get());//��� p
	}
}
