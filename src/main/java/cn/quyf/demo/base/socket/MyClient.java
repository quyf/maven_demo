package cn.quyf.demo.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",8000);
		
		BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream()));
		PrintWriter out = new PrintWriter( socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
		 while( true ){
			 String line = reader.readLine();
			 out.println( line );
			 out.flush();
			 if( line.equals("bye")){
				 break;
			 }
			 System.out.println(in.readLine());
		 }
		 socket.close();
	}
}
