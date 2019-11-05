package cn.quyf.demo.socket.rpcframework;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcFramework {

	/**
	 * 暴露服务
	 * @param service
	 * @param port
	 * @throws IOException
	 */
	public static void export(final Object service, int port) throws IOException {
		if (service == null)  
            throw new IllegalArgumentException("service instance == null");  
		if( port<0 || port>65535 )
			throw new IllegalArgumentException("Invalid port " + port);  
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);  
        ServerSocket server = new ServerSocket( port );
        for(;;){
        	try{
        		final Socket socket = server.accept();
        		new Thread(new Runnable(){

					@Override
					public void run() {
						try {
							ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
							String methodName = input.readUTF();
							Class<?>[] paramTypes = (Class<?>[])input.readObject();
							Object[] args = (Object[])input.readObject();
							ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
							Method method = service.getClass().getMethod(methodName, paramTypes);
							Object result = method.invoke(service, args);
							out.writeObject(result);
							
							out.close();
							input.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
        			
        		}).start();;
        	}catch(Exception e){
        		 e.printStackTrace();  
        	}
        }
	}
	/**
	 * 调用服务
	 * @param interfaceClass
	 * @param host
	 * @param port
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T refer(final Class<T> interfaceClass, final String host, final int port){
		 if (interfaceClass == null)  
	            throw new IllegalArgumentException("Interface class == null");  
		 if( !interfaceClass.isInterface())
			 throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!"); 
		 if (host == null || host.length() == 0)  
	            throw new IllegalArgumentException("Host == null!");  
        if (port <= 0 || port > 65535)  
            throw new IllegalArgumentException("Invalid port " + port);  
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);  
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, 
        		new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Socket client = new Socket(host, port);
						try {
							ObjectOutputStream out = new ObjectOutputStream( client.getOutputStream());
							try{
								out.writeUTF(method.getName());
								out.writeObject(method.getParameterTypes());
								out.writeObject(args);
								ObjectInputStream input = new ObjectInputStream(client.getInputStream());  
								try{
									Object result = input.readObject();
									return result;  
								}finally{
									input.close();
								}
							}finally{
								out.close();
							}
						}finally{
							client.close();
						}
					}
				});
		 
	}
}
