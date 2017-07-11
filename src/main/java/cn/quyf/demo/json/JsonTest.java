package cn.quyf.demo.json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class JsonTest {

	public static void main(String[] args) {
		String themInfo = " [{\"theme_id\":1,\"theme_name\":\"我们\"},{\"theme_id\":0,\"theme_name\":\"我们的\"}]";
		List<Theme> list  = JSON.parseArray(themInfo, Theme.class);
		
		System.out.println( list.toString());
		HashMap<String,Integer> map = new LinkedHashMap<String,Integer>();
		map.put("1", 1);
		map.put("3", 1);
		map.put("2", 1);
		map.remove("3");
		System.out.println( map);
	}
}
