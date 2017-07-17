package cn.quyf.simplespringmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.quyf.simplespringmvc.annotation.Controller;
import cn.quyf.simplespringmvc.annotation.Qualifier;
import cn.quyf.simplespringmvc.annotation.RequestMapping;
import cn.quyf.simplespringmvc.annotation.RequestParam;
import cn.quyf.simplespringmvc.service.HelloService;

@Controller
@RequestMapping("/test")
public class MyController {

	@Qualifier("helloServiceImpl")
	HelloService helloService;
	
	@RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, Map map,
            @RequestParam("name") String userName, List list) {
        try {
            PrintWriter pw = response.getWriter();
            String result = helloService.sayHello();
            pw.write(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@RequestMapping("/insert")
    public void insert(HttpServletRequest request,
            HttpServletResponse response, String param) {
        try {
            PrintWriter pw = response.getWriter();
            String result = "insert controller";
            
            pw.write(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/update")
    public void update(HttpServletRequest request,
            HttpServletResponse response, String param) {
        try {
            PrintWriter pw = response.getWriter();
            String result = "update controller";
            
            pw.write(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
