package cn.quyf.leetcode.search;

/**
 * 给定一个排序数组和一个目标值，如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置。
 * 你可以假设在数组中无重复元素。
 * <p>
 * 样例
 * [1,3,5,6]，5 → 2
 * [1,3,5,6]，2 → 1
 * [1,3,5,6]， 7 → 4
 * [1,3,5,6]，0 → 0
 * 原文链接：https://blog.csdn.net/wutingyehe/article/details/46899439
 *
 * @author quyf
 * @date 2019/11/12 14:12
 */
public class SearchInsertPositionDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 6, 7, 8, 10};
        int target = 5;
        int index = findPosition(arr, target);
        System.out.println(index);

        index = findPosition2(arr, target);
        System.out.println(index);
    }

    /**
     * 从0开始遍历
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findPosition(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= target && arr[i + 1] > target) {
                System.out.println("i=" + i + "," + arr[i] + "-" + arr[i + 1]);
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找方法
     *
     * @param arr
     * @param target
     * @return
     */
    private static int findPosition2(int[] arr, int target) {
        int l = 0;
        int len = arr.length - 1;
        while (l <= len) {
            //找到中间的位置
            int mid = l + (len - l) / 2;
            System.out.println("i=" + l + ",l=" + l + ", len=" + len+", mid="+mid);
            if (arr[mid] < target) {
                l = mid + 1;
            } else if (arr[mid] > target) {
                len = mid - 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
