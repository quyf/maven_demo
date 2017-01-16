package cn.quyf.demo.base.mbeans;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class MBeanAgent {

	public static void main(String[] args) 
			throws MalformedObjectNameException, InstanceAlreadyExistsException, 
			MBeanRegistrationException, NotCompliantMBeanException {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		//ObjectName的命名规范  domain:(key=value)+
		ObjectName objName = new ObjectName("cn.quyf.demo.base:type=MBeanTest");
		My mbtest = new My();
		server.registerMBean( mbtest, objName);
		System.out.println("waiting here for remote management...");  
        try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
