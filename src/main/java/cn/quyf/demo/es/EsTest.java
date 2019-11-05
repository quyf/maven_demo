package cn.quyf.demo.es;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author quyf
 * @date 2019/3/22 8:25
 */
public class EsTest {
    private TransportClient client;
    @Before
    public void getClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name","lrts_search_cluster")
                .build();
        
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.21.7"),9300));
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.21.8"),9300));
    }
    
    @Test
    public void createIndex(){
        CreateIndexResponse response = client.admin().indices().prepareCreate("blog").get();
        System.out.println(JSON.toJSONString(response));
        client.close();
    }

    /**
     * 相当于按默认的创建了参数创建了索引，
     * 然后插入了一条数据
     */
    @Test
    public void createIndexWithData(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","blog1");
        map.put("url","https://www.cnblogs.com/xbq8080/p/8995051.html");
        
        IndicesAdminClient adminClient = client.admin().indices();
        
        IndexResponse response = client.prepareIndex("bolgwithsettings","blog","2")
                .setSource(map)
                .get();
        
        System.out.println( response.toString());
    }

    /**
     * 按指定参数创建了索引，但设置不了数据
     */
    @Test
    public void createIndexWithSettings(){
        Settings settings = Settings.builder()
                .put("number_of_shards","2")
                .put("number_of_replicas","1")
                .build();
        client.admin().indices().prepareCreate("blogwith").setSettings(settings).get();
    }

    /**
     * 单独更新索引的个别参数，但有些参数不能修改更新，比如number_of_shards
     */
    @Test
    public void updateSettings(){
        IndicesAdminClient indices = client.admin().indices();
        
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("index.number_of_replicas", 1);
        //settingsBuilder.put("index.number_of_shards",2);
        settingsBuilder.put("index.refresh_interval", "60s");
        UpdateSettingsRequestBuilder settingsRequest = indices.prepareUpdateSettings("bolgwithsettings");
        settingsRequest.setSettings(settingsBuilder.build());
        UpdateSettingsResponse response = indices.updateSettings(settingsRequest.request()).actionGet();

        System.out.println(response.toString());
    }
    @Test
    public void deleteIndex(){
        client.admin().indices().prepareDelete("blogwith").get();
        client.close();
    }
    
    @Test
    public void updateIndexByMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","world");
        map.put("url","https://www.baidu.com/xbq8080/p/8995051.html");
        IndexResponse response = client.prepareIndex("blogwith","blog","10")
                .setSource(map).get();
        System.out.println(response);

//        map = new HashMap<String, Object>();
//        map.put("name","hello祝福你我们的大中国");
//        map.put("url","https://www.csdn.com/xbq8080/p/8995051.html");
//        response = client.prepareIndex("blogwith","blog","12")
//                .setSource(map).get();
//        System.out.println(response);
//
//        map = new HashMap<String, Object>();
//        map.put("name","中华人民共和国");
//        map.put("url","https://www.baidu.com/xbq8080/p/8995051.html");
//        response = client.prepareIndex("blogwith","blog","11")
//                .setSource(map).get();
//        System.out.println(response);
    }
    
    @Test
    public void getIndexData(){
        GetResponse response = client.prepareGet("blogwith","blog","12").get();
        System.out.println(response.getSourceAsString());
    }
    @Test
    public void deleteIndexData(){
        DeleteResponse response = client.prepareDelete("blogwith","a","1").get();
        System.out.println(response.toString());
    }
    
    @Test
    public void updateIndexData(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","hello");
        UpdateResponse response = client.prepareUpdate("blogwith","blog","9")
                .setDoc(map).get();
        System.out.println(response.toString());
    }

    @Test
    public void updateIndexData2(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","hello中zhongworld");
        
        UpdateRequest request = new UpdateRequest();
        request.id("10");
        request.type("blog");
        request.index("blogwith");
        request.doc(map);

//        request.doc(XContentFactory.jsonBuilder()
//                .startObject()
//                .field("gender", "male")
//                .endObject());

        UpdateResponse response = client.update(request).actionGet();
        System.out.println(response.toString());
    }
    
    @Test
    public void mutilGet(){
        //https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.2/java-docs-multi-get.html
        MultiGetResponse responses = client.prepareMultiGet()
                .add("blogwith","blog",Lists.newArrayList("1","10","11")).get();
        for(MultiGetItemResponse item:responses){
            GetResponse rep = item.getResponse();
            if(rep.isExists()){
                System.out.println(rep.getSourceAsString());
            }
        }
    }

    /**
     * 查询所有记录
     */
    @Test
    public void matchQllQuery(){
        SearchResponse response = client.prepareSearch("blogwith")
                //.setTypes("blog")
                .setQuery(QueryBuilders.matchAllQuery()).get();
        SearchHits hits = response.getHits();
        System.out.println("命中多少条："+hits.getTotalHits());
        for(SearchHit searchHit:hits){
            System.out.println(searchHit.getSourceAsString());
        }
       
    }

    /**
     * 对所有字段进行 匹配查询，注意中英文的区别，可能涉及到底层分词
     */
    @Test
    public void queryStringQuery(){
        SearchResponse response = client.prepareSearch("blogwith")
                //.setTypes("blog")
                //这种写法es会把中国拆分成 中 和 国字分别去搜索 不需要两个字相邻
                .setQuery(QueryBuilders.queryStringQuery("hello world"))
                //下面这行也是 会把中国拆分成中 和 国字分别去搜索 不需要两个字相邻，唯一多的条件时匹配时 必须同时包含 中 AND 国
                // .setQuery(new QueryStringQueryBuilder("中国").field("name").defaultOperator(Operator.AND))
                //如果不想拆分，如果需要检索包含“中国”的，则需要写成如下 （加上 双引号 表示强制要求 拆分一起）
                //.setQuery(new QueryStringQueryBuilder("\""+"中国"+"\"").field("name").defaultOperator(Operator.AND))
                .get();
        SearchHits hits = response.getHits();
        System.out.println("命中多少条："+hits.getTotalHits());
        for(SearchHit searchHit:hits){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    /**
     * 通配符查询（中文会受分词结果影响）
     * * ：表示多个字符（0个或多个字符）
     * ？：表示单个字符
     */
    @Test
    public void willcardQuery(){
        SearchResponse response = client.prepareSearch("blogwith")
                //.setTypes("blog")
                .setQuery(QueryBuilders.wildcardQuery("name","*中国*")).get();
        SearchHits hits = response.getHits();
        System.out.println("命中多少条："+hits.getTotalHits());
        for(SearchHit searchHit:hits){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    /**
     * 完全匹配查询，结果受分词影响
     */
    @Test
    public void tremQuery(){
        SearchResponse response = client.prepareSearch("blogwith")
                //.setTypes("blog")
                .setQuery(QueryBuilders.termQuery("name","hello world")).get();
        SearchHits hits = response.getHits();
        System.out.println("命中多少条："+hits.getTotalHits());
        for(SearchHit searchHit:hits){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    /**
     * 模糊查询，
     */
    @Test
    public void fuzzy(){
        SearchResponse response = client.prepareSearch("blogwith")
                //.setTypes("blog")
                .setQuery(QueryBuilders.termQuery("name","hello world")).get();
        SearchHits hits = response.getHits();
        System.out.println("命中多少条："+hits.getTotalHits());
        for(SearchHit searchHit:hits){
            System.out.println(searchHit.getSourceAsString());
        }
    }

    @Test
    public void createMapping() throws Exception {
        // 1设置mapping
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("article")
                .startObject("properties")
                .startObject("id1")
                .field("type", "string")
                .field("store", "yes")
                .endObject()
                .startObject("title2")
                .field("type", "string")
                .field("store", "no")
                .endObject()
                .startObject("content")
                .field("type", "string")
                .field("store", "yes")
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        // 2 添加mapping
        PutMappingRequest mapping = Requests.putMappingRequest("blog4").type("article").source(builder);

        client.admin().indices().putMapping(mapping).get();

        // 3 关闭资源
        client.close();
    }

    /**
     * 创建使用ik分词器的mapping
     * @throws Exception
     */
    @Test
    public void createMappingWitIkanalyze() throws Exception {

        // 1设置mapping
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("article")
                .startObject("properties")
                .startObject("id1")
                .field("type", "string")
                .field("store", "yes")
                .field("analyzer","ik_smart")
                .endObject()
                .startObject("title2")
                .field("type", "string")
                .field("store", "no")
                .field("analyzer","ik_smart")
                .endObject()
                .startObject("content")
                .field("type", "string")
                .field("store", "yes")
                .field("analyzer","ik_smart")
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        // 2 添加mapping
        PutMappingRequest mapping = Requests.putMappingRequest("blog4").type("article").source(builder);
        client.admin().indices().putMapping(mapping).get();

        // 3 关闭资源
        client.close();
    }

    
}