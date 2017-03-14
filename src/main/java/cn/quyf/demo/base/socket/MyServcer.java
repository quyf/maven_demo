package cn.quyf.demo.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServcer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket( 8000);
		while( true ){
			Socket client = server.accept();
			if( client==null ){
				continue;
			}
			System.out.println("begin accept////");
			new Thread(new Acceptor(client)).start();
		}
	}
}

class Acceptor implements Runnable{
	private Socket client;
	
	public Acceptor(Socket client){
		this.client = client;
	}
	
	public void run() {
		try {
		BufferedReader in = new BufferedReader(new InputStreamReader( client.getInputStream() ));
		PrintWriter out = new PrintWriter( client.getOutputStream());
		while (true){
			String line = in.readLine();
			System.out.println("server receive:"+line);
			out.println( line );
			out.flush();
			if( line.equals("bye")){
				break;
			}
		}
		client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}