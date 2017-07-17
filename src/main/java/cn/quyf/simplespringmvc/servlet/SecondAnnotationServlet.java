package cn.quyf.simplespringmvc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 利用Servlet3.0新特性@WebServlet注释
 * @author quyf
 * @date 2017年7月7日
 */
//@WebServlet("/second")
public class SecondAnnotationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public SecondAnnotationServlet() {
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
