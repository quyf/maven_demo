package cn.quyf.simplespringmvc.argumentResolver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.quyf.simplespringmvc.annotation.Service;

@Service("mapAgrumentResolver")
public class MapArgumentResolver implements ArgumentResolver{

	@Override
	public boolean support(Class<?> type, int paramIndex, Method method) {
		return Map.class.isAssignableFrom(type);
	}

	@Override
	public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type,
			int paramIndex, Method method) {
		Map<String,String[]> paramMap = request.getParameterMap();
		Map<String,String> map = new HashMap<String,String>();
		for(Map.Entry<String, String[]> entry:paramMap.entrySet()){
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		return map;
	}

}
