package cn.quyf.demo.es;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class EsUtil {

	public static void main(String[] args) {
		EsUtil util = new EsUtil();
		Client client = EsClientFactory.getInstance().getClient();
		//util.createIndex(client);
		//util.deleteIndex(client, ".kibana");
		util.search(client, "index_2", "json", "2");
	}
	
	public void search(Client client, String index, String type, String id){
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(type);
		searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
		// 默认不返回source	 
		searchRequestBuilder.setFetchSource(true);
		String [] indices = searchRequestBuilder.request().indices();
		for(String s:indices){
			System.out.println("search idices:"+s);//打印的是索引名
		}
		SearchResponse resp = searchRequestBuilder.execute().actionGet();
		SearchHits searchHits = resp.getHits();
		System.out.println("总数："+searchHits.getTotalHits());  
		SearchHit[] hits = searchHits.getHits();
		for(SearchHit hit : hits){
			String j = hit.getSourceAsString();//fetchsource=true可以这样获取，false的话只能拿到id
			System.out.println(j);
		}
	}
	
	private void deleteIndex(Client client, String index){
		IndicesAdminClient indices = client.admin().indices();
		DeleteIndexResponse deleteResponse = indices.delete(indices.prepareDelete(index).request()).actionGet();
		if(deleteResponse.isAcknowledged()){
			System.out.println("删除索引成功");
		}else{
			System.out.println("删除不成功");
		}
	}
	
	private void createIndex(Client client){
		String indexName = "index_2";
		IndexResponse response = indexByJsonString(client, indexName, "json", "2");
		String _type = response.getType();
		String id = response.getId();
		String _index = response.getIndex();
		System.out.println("by json string \t_index="+_index+"\t_type="+_type+"\tid=" + id );
		response = indexByMap(client, indexName, "map", "1");
		 _type = response.getType();
		 id = response.getId();
		 _index = response.getIndex();
		System.out.println("by map \t_index="+_index+"\t_type="+_type+"\tid=" + id );
		
		response = indexByMap(client, indexName, "xcountbuilder", "2");
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
		IndexResponse response = client.prepareIndex(index, type, id).setSource(json).get();
		return response;
	}
}
