package cn.quyf.demo.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;

public class EsClient {
	public static void main(String[] args) throws Exception {
		EsClient cc = new EsClient();
		Settings settings = Settings.builder().put("cluster.name","").build();

        TransportClient  client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
		//不同版本写法不一样
//                TransportClient.builder().settings(settings).build()
//				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		//创建索引
		cc.createIndex(client);
		//检索
		GetRequestBuilder getRequestBuilder = client.prepareGet("index_1", "json", "1");
		System.out.println(getRequestBuilder.get().getSourceAsString());
		
		GetResponse getResponse = client.prepareGet("index_1", "json", "1").get();
		System.out.println("GetResponse\t"+getResponse.getSourceAsString());
		
		//delete 
		DeleteResponse response = client.prepareDelete("index_1", "test", "1").get();
		System.out.println( "DeleteResponse");
		
		//update 
		UpdateRequest updateReq = new UpdateRequest();
		updateReq.index("index_1").type("json").id("1");
		updateReq.doc("user","quyf");
		client.update(updateReq).get();
		
		//select
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch("index_1");
		//searchRequestBuilder.setTypes(typeName);
		searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
		searchRequestBuilder.setFetchSource(true);//是否返回source
		searchRequestBuilder.setQuery(QueryBuilders.termQuery("user", "kimchy"));
		//执行查询  
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();  
		SearchHits searchHits = searchResponse.getHits();  
		System.out.println("总数："+searchHits.getTotalHits());  
		SearchHit[] hits = searchHits.getHits();  
		for (SearchHit hit : hits) {  
		    String json = hit.getSourceAsString();  
		    System.out.println( json );
		}  
		
	}
	
	private void createIndex(Client client){
		IndexResponse response = indexByJsonString(client, "index_1", "json", "2");
		String _type = response.getType();
		String id = response.getId();
		String _index = response.getIndex();
		System.out.println("by json string \t_index="+_index+"\t_type="+_type+"\tid=" + id );
		response = indexByMap(client, "index_1", "map", "1");
		 _type = response.getType();
		 id = response.getId();
		 _index = response.getIndex();
		System.out.println("by map \t_index="+_index+"\t_type="+_type+"\tid=" + id );
		
		response = indexByMap(client, "index_1", "xcountbuilder", "2");
		 _type = response.getType();
		 id = response.getId();
		 _index = response.getIndex();
		System.out.println("by xcountbuilder \t_index="+_index+"\t_type="+_type+"\tid=" + id );
	}
	
	@SuppressWarnings("unused")
	private IndexResponse indexByXContentBuilder(Client client,String index,String type,String id){
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
					.field("user","quyf")
					.field("postDate", new Date())
					.field("message","indexByXContentBuilder")
					.endObject();
			IndexResponse response = client.prepareIndex(index, type, id).setSource( builder.string()).get();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private IndexResponse indexByMap(Client client,String index,String type,String id){
		HashMap<String, Object> json = new HashMap<String, Object>();
		json.put("user","kimchy");
		json.put("postDate",new Date());
		json.put("message","indexByMap");
		IndexResponse indexResponse = client.prepareIndex(index, type, id).setSource(json).get();
		return indexResponse;
	}
	
	private IndexResponse indexByJsonString(Client client,String index,String type,String id){
		String json = "{" +
		        "\"user\":\"kimchy\"," +
		        "\"postDate\":\"2013-01-30\"," +
		        "\"message\":\"indexByJsonString\"" +
		    "}";
		IndexResponse response = client.prepareIndex(index, type, id)
				.setSource(json).get();
		return response;
	}
}
