package cn.quyf.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2019-11-19
 */
public class IntegerReverse {

	public static void main(String[] args) {
		System.out.println( reverse(123));
        System.out.println( reverse2(1234));
	}

    /**
     * 思路：将整数的每一位拿出来放进列表中，再根据列表元素*10累加
     * @param x
     * @return
     */
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

    /**
     * 1234
     * 将每次的末位拿出来，下次的话 它就要x10 然后再累加
     * 123  4
     * 12  4*10 + 3
     * 1 （4*10+3）*10 + 2
     * (（4*10+3）*10 + 2）*10 +1
     * @param x
     * @return
     */
    public static int reverse2(int x){
	    int sum = 0;
        int tmp = x;
        while (tmp!=0){
            int mod = tmp%10;
            tmp = tmp/10;
            sum = sum*10 + mod;
        }
        return sum;
    }
}