package cn.quyf.simplespringmvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public FirstServlet() {
		System.out.println("constr");
		System.out.println("parent "+this.getClass().getClassLoader());
	}
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println( "init...");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		System.out.println("post");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("destory");
	}
	
}
