package cn.quyf.demo.es;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

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
		Settings.Builder setting = Settings.settingsBuilder();
		setting.put("cluster.name", "my-es");
		// 设置
		setting.put("node.master", false);
		setting.put("node.data", false);
		setting.put("bootstrap.mlockall", true);
		setting.put("http.enabled", false);
		TransportClient tc = TransportClient.builder().settings(setting).build();
//			tc.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300) );
//			tc.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9301) );
//			tc.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9302) );
			
		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9300)) );
		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9301)) );
		tc.addTransportAddresses(new InetSocketTransportAddress(new InetSocketAddress("192.168.2.93", 9302)) );
		this.client = tc;
	}
	
	/**
	 * 获取ES客户端
	 * @return
	 */
	public Client getClient(){
		return client;
	}
}
