package cn.quyf.demo.json;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonTest {

	public static void main(String[] args) {
		boolean f1 = true;
		boolean f2 = false;
		System.out.println( f1==f2);

		System.out.println(1000023411%8);
		System.out.println(1000023411/8%100);
		Map<String, BigDecimal> priceMap = new HashMap<>();
		priceMap.put("1",new BigDecimal(12));
		priceMap.put("3",new BigDecimal(1));
		priceMap.put("2",new BigDecimal(10));
		System.out.println(JSON.toJSONString(priceMap));

	}

	private static void testArray(){
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
