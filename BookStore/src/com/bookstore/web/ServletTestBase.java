package com.bookstore.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class ServletTestBase extends BaseServlet {
	
	public void hello(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("≤‚ ‘ BaseServlet,hello world.......");
	}
}
