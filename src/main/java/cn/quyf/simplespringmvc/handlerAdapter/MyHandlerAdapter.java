package cn.quyf.simplespringmvc.handlerAdapter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.quyf.simplespringmvc.annotation.Service;
import cn.quyf.simplespringmvc.argumentResolver.ArgumentResolver;

@Service("MyHandlerAdapter")
public class MyHandlerAdapter implements HandlerAdapter {

	@Override
	public Object[] hand(HttpServletRequest request, HttpServletResponse response, Method method,
			Map<String, Object> beans) {
		Class<?>[] classParams = method.getParameterTypes();
		Object[] args = new Object[ classParams.length];
		Map<String,Object> argumentResolvers = getBeansOfType(beans, ArgumentResolver.class);
		
		int paramIndex = 0;//参数的下标
		int i = 0;// args数组的下标
		
		for(Class<?> paramClazz:classParams){//遍历参数的class类型
			for(Map.Entry<String, Object> entry:argumentResolvers.entrySet()){//拿到所有参数解析类
				System.out.println(entry.getKey());
				ArgumentResolver ar = (ArgumentResolver) entry.getValue();
				if( ar.support(paramClazz, paramIndex, method)){
					args[i++] = ar.argumentResolver(request, response, paramClazz, paramIndex, method);
				}
			}
			paramIndex ++;
		}
		return args;
	}

	private Map<String, Object> getBeansOfType(Map<String, Object> beans, Class<?> type){
		Map<String, Object> resultBeans = new HashMap<String, Object>();
		for(Map.Entry<String, Object> entry:beans.entrySet()){//xxxArgumentResolver:xxxArgumentResolver.instance
			Class<?>[] interfaces = entry.getValue().getClass().getInterfaces();
			if( interfaces!=null && interfaces.length>0 ){
				for(Class<?> clzz:interfaces){
					if(clzz.isAssignableFrom(type)){
						System.out.println(entry.getKey()+" == "+entry.getValue());
						resultBeans.put(entry.getKey(), entry.getValue());
					}
				}
			}
		}
		return resultBeans;
	}
}
