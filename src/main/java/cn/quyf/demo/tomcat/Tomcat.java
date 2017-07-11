package cn.quyf.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {

	public int port;
	
	public static void main(String[] args) throws IOException {
		start(8080);
	}
	public static void start(int port) throws IOException{
		ServerSocket server =  new ServerSocket( port );
		
		while( true ){
			Socket client = server.accept();
			InputStream in = client.getInputStream();
			OutputStream out = client.getOutputStream();
			byte[] buff = new byte[1024];
			int len =0;
			String content = "";
			if( (len=in.read(buff))>0 ){
				content = new String(buff,0,len);
			}
			System.out.println( content );
			out.write("hello world".getBytes());
			//out.flush();
//			in.close();
//			out.close();
			client.close();
		}
	}
}
