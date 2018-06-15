package cn.quyf.leetcode;

import java.util.ArrayList;
import java.util.List;

public class IntegerReverse {

	public static void main(String[] args) {
		System.out.println( reverse(-0));
	}
	
	public static int reverse(int x) {
		List<Integer> intList = new ArrayList<Integer>();
        for(int i=1; i<Math.abs(x); i=i*10){
        	intList.add(x/i%10);
        }
        int rt = 0;
        for(int i=0; i<intList.size(); i++){
        	rt = rt + (int) (intList.get(i) * Math.pow(10, (intList.size()-i -1)));
        }
		return rt;
    }
}
