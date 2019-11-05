package cn.quyf.simplespringmvc.argumentResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.quyf.simplespringmvc.annotation.RequestParam;
import cn.quyf.simplespringmvc.annotation.Service;

@Service("requestParamArgumentResolver")
public class RequestParamArgumentResolver implements ArgumentResolver {

	@Override
	public boolean support(Class<?> type, int paramIndex, Method method) {
		Annotation[][] an = method.getParameterAnnotations();
		Annotation[] paramAns = an[paramIndex];
		for(Annotation paramAn:paramAns){
			if( RequestParam.class.isAssignableFrom(paramAn.getClass())){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type,
			int paramIndex, Method method) {
		Annotation[][] annos = method.getParameterAnnotations();
		Annotation[] annoParams = annos[paramIndex];
		for(Annotation ann:annoParams){
			if(RequestParam.class.isAssignableFrom(ann.getClass())){
				RequestParam req = (RequestParam)ann;
				String value = req.value();
				return request.getParameter(value);
			}
		}
		return null;
	}

}
