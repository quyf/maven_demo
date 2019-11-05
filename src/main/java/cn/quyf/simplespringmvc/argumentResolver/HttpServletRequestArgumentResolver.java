package cn.quyf.simplespringmvc.argumentResolver;

import java.lang.reflect.Method;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.quyf.simplespringmvc.annotation.Service;

@Service("httpServletRequestArgumentResolver")
public class HttpServletRequestArgumentResolver implements ArgumentResolver{

	@Override
	public boolean support(Class<?> type, int paramIndex, Method method) {
		return ServletRequest.class.isAssignableFrom(type);
	}

	@Override
	public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type,
			int paramIndex, Method method) {
		return request;
	}

}
