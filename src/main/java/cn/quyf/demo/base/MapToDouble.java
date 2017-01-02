package cn.quyf.demo.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明 
 *
 * @author Van
 * @date 2016-12-26
 */
public class MapToDouble {

	public static void main(String[] args) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
	    returnMap.put("card_money", 100f);
        returnMap.put("card_left_money", 12f);
        returnMap.put("card_left_given_money", 12d);
        Float card_money = Float.parseFloat( returnMap.get("card_money").toString());
        Float card_left_money = Float.parseFloat( returnMap.get("card_left_money").toString());
        Float card_left_given_money = Float.parseFloat( returnMap.get("card_left_given_money").toString());
        
        System.out.println( card_money );
        System.out.println( card_left_money );
        System.out.println( card_left_given_money );
	}
}
