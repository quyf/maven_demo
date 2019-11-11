package cn.quyf.leetcode;

/**
 * https://blog.csdn.net/mine_song/article/details/70049821
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * 
 * @author quyf
 * @date 2019/11/8 18:06
 */
public class GasStationByteDanceDemo {
    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        
        int index = findWayOne(gas, cost);

        System.out.println("从园区："+gas[index]+" 开始可绕行一周");

        index = findWayMy(gas, cost);

        System.out.println("从园区："+gas[index]+" 开始可绕行一周");
    }

    /**
     * 如果A到B不能到达，则 A 到B之间的点也不能到达B点。如果所有油不够所有消耗，则不能完成行程。
     * @param gas
     * @param cost
     * @return
     */
    private static int findWayOne(int[] gas, int[] cost) {
        //跑到下个点所剩下的油
        int left = 0;
        //由于是环路，但是数组定义的单向加油站，所以再每次遍历的时候，要记住过去没跑成功的剩下的汽油
        int oldLeft = 0;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            left += gas[i] - cost[i];
            if (left < 0) {
                oldLeft += left;
                left = 0;
                index = i + 1;
            }
        }
        if (oldLeft + left < 0) {
            return -1;
        }
        return index;
    }

    public static int findWayMy(int[] gas, int[] cost){
        int index = 0;
        while (true) {
            int gasCost = 0;
            int gasOwn = 0;
            for (int i = 0; i < gas.length; i++) {
                gasCost += cost[i];
                gasOwn += gas[i];
            }
            for (int i = 0; i < index; i++) {
                gasCost += cost[i];
                gasOwn += gas[i];
            }
            if (gasOwn >= gasCost) {
                break;
            }else {
                index = -1;
            }
        }
        return index;
    }
}
