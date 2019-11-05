package cn.quyf.simplespringmvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.quyf.simplespringmvc.annotation.Controller;
import cn.quyf.simplespringmvc.annotation.Qualifier;
import cn.quyf.simplespringmvc.annotation.RequestMapping;
import cn.quyf.simplespringmvc.annotation.Service;
import cn.quyf.simplespringmvc.handlerAdapter.HandlerAdapter;

public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static String HANDLERADAPTER = "com.quyf.springmvc.handlerAdapter";
	  
	List<String> classNames = new ArrayList<String>();
	Map<String,Object> beans = new HashMap<String,Object>();
	Map<String,Object> handlerMap =  new HashMap<String,Object>();
	
	Properties pros;
	
	public DispatcherServlet() {
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//扫描包
		scanPackage("com.quyf.springmvc");
		for(String str:classNames){
			System.out.println(str.toString());
		}
		//实例化所有被扫描出来的类
		instance();
		for(Entry<String,Object> entry:beans.entrySet()){
			System.out.println( entry.getKey()+"==="+entry.getValue());
		}
		ioc();
		handleMapping();
		System.out.println("pring handler mapping result");
		for(Entry<String,Object> entry:handlerMap.entrySet()){
			System.out.println( entry.getKey()+"==url mapping=="+entry.getValue());
		}
		InputStream in = this.getClass()
				.getResourceAsStream("/config/properties/spring.properties");
		pros = new Properties();
		try {
			pros.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void handleMapping(){
		for (Entry<String, Object> entry : beans.entrySet()) {
			Object instance = entry.getValue();
			Class clzz = instance.getClass();
			String controllerUrl = "";
			if( clzz.isAnnotationPresent(RequestMapping.class)){//看类上是否有requestMapping注释
				RequestMapping classPath =(RequestMapping) clzz.getAnnotation(RequestMapping.class);
				controllerUrl = classPath.value();
			}
			//看各个方法上是否有注释
			Method[] methods = clzz.getDeclaredMethods(); //clzz.getMethods();
			for(Method method:methods){
				if( method.isAnnotationPresent(RequestMapping.class)){
					RequestMapping methodMap = (RequestMapping)method.getAnnotation(RequestMapping.class);
					handlerMap.put(controllerUrl+methodMap.value(), method);
				}
				continue;
			}
		}
	}
	
	private void ioc(){
		if (beans.entrySet().size() <= 0) {
			System.out.println("没有类的实例化！");
			return;
		}
		for (Entry<String, Object> entry : beans.entrySet()) {
			Object instance = entry.getValue();
			Class cls = instance.getClass();
			if( cls.isAnnotationPresent(Controller.class)){
				Field[] fields = cls.getDeclaredFields();
				for(Field field:fields){
					if( field.isAnnotationPresent(Qualifier.class)){
						Qualifier qua = field.getAnnotation( Qualifier.class);
						field.setAccessible(true);
						try {
							field.set(instance, beans.get(qua.value()));
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
		  
	}
	
	private void instance() {
		if( classNames.size()<0){
			System.out.println("扫描失败");
			return ;
		}
		for(String className:classNames){
			String cn = className.replace(".class", "");
			try {
				Class classzz = Class.forName(cn);
				if( classzz.isAnnotationPresent(Controller.class)){
					Controller controller = (Controller)classzz.getAnnotation(Controller.class);
					Object obj = classzz.newInstance();
					RequestMapping requestMapping = (RequestMapping) classzz.getAnnotation(RequestMapping.class);
					beans.put(requestMapping.value(), obj);
				}else if( classzz.isAnnotationPresent(Service.class)){
					Service service = (Service)classzz.getAnnotation(Service.class);
					Object instance = classzz.newInstance();
					beans.put(service.value(), instance);
				}else{
					continue;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void scanPackage(String basePackage) {
		URL url = this.getClass().getClassLoader().getResource("/"+replaceTo(basePackage));
		String scanFile = url.getFile();
		File fileStr = new File(scanFile);
		String[] fileList = fileStr.list();
		for(String path:fileList){
			File file = new File(fileStr+"/"+path);
			if( file.isDirectory()){
				scanPackage(basePackage+"."+path);
			}else{
				classNames.add(basePackage+"."+file.getName());
			}
		}
	}

	private String replaceTo(String basePackage){
		return basePackage.replaceAll("\\.", "/");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		Method method = (Method) handlerMap.get(uri);
		Object obj = beans.get("/"+uri.split("/")[1]);
		
		HandlerAdapter adapter = (HandlerAdapter)beans.get( pros.getProperty(HANDLERADAPTER) );
		Object[] args = adapter.hand(req, resp, method, beans);
		try {
			method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}