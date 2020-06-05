package cn.quyf.demo.base;

import com.alibaba.fastjson.JSON;

import java.util.Optional;

public class Simple {


	public Simple(){

	}
	public static void main(String[] args) {
		Integer a = 3;
		System.out.println(Optional.ofNullable(a).orElse(0).intValue());
		Simple s1=new Simple();

		System.out.println(JSON.toJSONString(s1));

		Simple simple = JSON.parseObject(null, Simple.class);
		System.out.println(simple);
	}
}
