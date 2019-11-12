package cn.quyf.leetcode.sort;

import java.util.Arrays;

/**
 * 二分查找法,在有序数组arr中,查找target,如果找到target,返回相应的索引index, 如果没有找到target,返回-1
 * @author quyf
 * @date 2019/11/5 12:00
 */
public class DivideSearchDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{32,40,50,60,75,80,90};
        int rt = binarySearch(arr, 0, arr.length-1, 75);
        System.out.println(rt);
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
        //防止溢位
        int mid = start + (end - start)/2;
        System.out.println(start +"-"+end+",mid="+mid);
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            return binarySearch(arr, start, mid - 1, target);
        }
        if(arr[mid] < target){
            return binarySearch(arr, mid + 1, end, target);
        }
        return mid;
    }
}
