package com.threadlocal;

public class TestThreadLocal {
	public static void main(String[] args) {
		ThreadLocal tl = new ThreadLocal();
		tl.set("p");
		MyThread mt = new MyThread(tl);
		/*
		   * 线程不一样  
		 */
		mt.start();//输出 nullaaaaaaaaaaaa
		System.out.println(tl.get());//输出 p
	}
}
