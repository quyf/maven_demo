package cn.quyf.leetcode.sort;

import java.util.Arrays;

/**
 * @author quyf
 * @date 2019/11/5 12:00
 */
public class DivideSearchDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{60,80,90,50,40,75,32};
        binarySearch(arr, 0, arr.length, 40);
    }
    /**
     * 二分查找（递归）
     * @param arr 数组
     * @param start 头索引
     * @param end 尾索引
     * @param target 目标
     * @return 目标索引
     */
    public static int binarySearch(int[] arr, int start, int end, int target){
        if (start > end) {
            return -1;
        }
        System.out.println(Arrays.toString(arr));
        //防止溢位
        int mid = start + (end - start)/2;    
        if (arr[mid] > target) {
            return binarySearch(arr, start, mid - 1, target);
        }
        if(arr[mid] < target){
            return binarySearch(arr, mid + 1, end, target);
        }
        return mid;
    }
}
