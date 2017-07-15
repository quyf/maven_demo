package cn.quyf.demo.os.redis;

import java.net.ServerSocket;
import java.net.Socket;

public class RedisVm {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(6379);
		while( true ){
			Socket client = server.accept();
			System.out.println(client.getPort());
			byte[] read = new byte[1024];
			
			client.getInputStream().read(read);
			String sre = new String(read);
			System.out.println(sre);
			client.getOutputStream().write("hello world".getBytes());
			client.getOutputStream().flush();
		}
	}
}
