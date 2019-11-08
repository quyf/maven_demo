package cn.quyf.leetcode.sort;

import java.util.Arrays;

/**
 * @author quyf
 * @date 2019/11/5 12:34
 */
public class QuickSort {

    public static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        //定义俩指针 用于移动
        int left = head;
        int right = tail;
        int pivot = arr[head]; //基准值，也可以arr[(head + tail) / 2]

        while (left <= right) {
            while (arr[left] < pivot) { //左指针先走，找到大于等于基准数的停止
                ++left;
            }
            while (arr[right] > pivot) { //右指针后走，找到小于等于基准数的停止
                --right;
            }
            if (left < right) {
                //交换arr[left]和arr[right]的位置
                int t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
                //继续遍历
                ++left;
                --right;
            } else if (left == right) {
                //遍历完，错开两指针
                ++left;
                //break;
            }
        }
        System.out.println(Arrays.toString(arr));
        qSort(arr, head, right);
        qSort(arr, left, tail);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        System.out.println(Arrays.toString(arr));
        qSort(arr, 0, arr.length - 1);
    }

}
