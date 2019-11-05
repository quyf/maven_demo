package cn.quyf.demo.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsClientFactory {

	public static void main(String[] args) {
		try {
			System.out.println(InetAddress.getByName("localhost"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static EsClientFactory esFactory;
	private Client client;
	
	public static EsClientFactory getInstance(){
		if(esFactory==null){
			EsClientFactory esFactory = new EsClientFactory();
			esFactory.buildClient();
			EsClientFactory.esFactory = esFactory;
		}
		return esFactory;
	}
	
	private void buildClient(){
		Settings setting = Settings.builder().put("cluster.name", "my-es").build();
		Client clt = new PreBuiltTransportClient(setting);
			
//		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9300)) );
//		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9301)) );
//		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9302)) );
		this.client = clt;
	}
	
	/**
	 * 获取ES客户端
	 * @return
	 */
	public Client getClient(){
		return client;
	}
}
