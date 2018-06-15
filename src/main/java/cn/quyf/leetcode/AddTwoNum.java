package cn.quyf.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given nums = [2, 7, 11, 15], target = 9,

   Because nums[0] + nums[1] = 2 + 7 = 9,
   return [0, 1].
 * @author quyf
 * @date 2018年1月26日
 */
public class AddTwoNum {
	
	public static void main(String[] args) {
		System.out.println( Arrays.toString(twoSum(new int[]{2, 11, 15, 7, 9}, 9)));
		System.out.println( Arrays.toString(twoSum(new int[]{1,-2,-3,-4,-5}, -8)));
		System.out.println( Arrays.toString(twoSum(new int[]{3, 3}, 6)));
		System.out.println( Arrays.toString(twoSum2(new int[]{0,3,0, 3,3}, 6)));
	}
	public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length; j++){
            	if(nums[i]+nums[j]==target){
            		return new int[]{i, j};
            	}
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
	
	public static int[] twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        map.put(nums[i], i);
	    }
	    System.out.println(map);
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement) && map.get(complement) != i) {
	            return new int[] { i, map.get(complement) };
	        }
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}
}
