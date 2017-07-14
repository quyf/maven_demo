package cn.quyf.demo.base.file;

import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ReadFileDemo {

	public static void main(String[] args) throws ConfigurationException {
		PropertiesConfiguration config = new PropertiesConfiguration("cn/quyf/demo/base/file/lrts_copyright.txt");
		System.out.println( config.getString("h_网络采集_h"));
		System.out.println( config);
	}
}
