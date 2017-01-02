package cn.quyf.demo.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明 
 *
 * @author Van
 * @date 2016-12-16
 */
public class MactherMain {

	 private static final Pattern PATH_PATTERN = Pattern.compile("(\".*?\")|(([^,])*)");
	 
	 public static void main(String[] args) {
		 String value = "\"${catalina.base}/lib\",\"${catalina.base}/lib/*.jar\",\"${catalina.home}/lib\",\"${catalina.home}/lib/*.jar\"";
		 
		 Matcher matcher = PATH_PATTERN.matcher(value);
		 
	}
}
