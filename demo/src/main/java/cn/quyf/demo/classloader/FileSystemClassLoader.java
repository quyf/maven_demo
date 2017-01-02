package cn.quyf.demo.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类说明
 * 
 * @author Van
 * @date 2016-12-20
 */
public class FileSystemClassLoader extends ClassLoader {
	private String rootDir;

	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}

	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassDatea( name );
		if( classData == null ){
			throw new ClassNotFoundException();
		}else{
			return defineClass(name, classData, 0, classData.length);
		}
		
	}

	private byte[] getClassDatea(String name) {
		String path = classNameToPath( name );
		try {
			InputStream ins = new FileInputStream( path );
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0; 
            while ((bytesNumRead = ins.read(buffer)) != -1) { 
            	out.write(buffer, 0, bytesNumRead); 
            } 
            return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String classNameToPath(String name) {
		String s =  rootDir + name.replace(".", "\\")+".class";
		System.out.println( s );
		return s;
	}

}
