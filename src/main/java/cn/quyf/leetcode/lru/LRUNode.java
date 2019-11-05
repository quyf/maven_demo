package cn.quyf.leetcode.lru;

/**
 * @author quyf
 * @date 2019/10/31 9:25
 */
public class LRUNode {
    String key;
    Object data;
    LRUNode pre;
    LRUNode next;

    public LRUNode(String key, Object data) {
        this.key = key;
        this.data = data;
    }
}
