package cn.quyf.demo.os.redis;

import java.io.IOException;
import java.net.Socket;

public class RedisClient {

	private static final String BREAK_LINE = "\r\n";
	private Socket socket;
	public RedisClient() throws IOException {
		this.socket = new Socket("localhost",6379);
	}
	
	public String get(String key) throws Exception{// *num $len cmd $len param
		//get a
		StringBuffer sb = new StringBuffer();
		sb.append("*2").append(BREAK_LINE);
		sb.append("$3").append(BREAK_LINE);
		sb.append("GET").append(BREAK_LINE);
		sb.append("$").append(key.getBytes().length).append(BREAK_LINE);
		sb.append(key).append(BREAK_LINE);
		socket.getOutputStream().write(sb.toString().getBytes());
		byte[] rt = new byte[2048];
		socket.getInputStream().read(rt);
		return new String(rt,"utf-8");
	}
	
	public void set(String key, String value){
		
	}
	
	public static void main(String[] args) throws Exception {
		RedisClient client = new RedisClient();
		String hello = client.get("hello");
		System.out.println( hello );
	}
	
}
