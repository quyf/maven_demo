package cn.quyf.leetcode.lru;

import java.util.HashMap;

/**
 * @author quyf
 * @date 2019/10/31 9:11
 * https://mp.weixin.qq.com/s?__biz=MzA4ODIyMzEwMg==&mid=2447534239&idx=1&sn=64d9ecfc97278307cf2fdc231528d6b7&chksm=843bb48eb34c3d98ad583900a386dc9766287829aecdd6e06d6c6401498049df1deb667361d7&scene=0&xtrack=1&key=042d6afab53eaf2ee2fee49435c02c8e152ee086e154f2b2e0ec9fcea2b7a33ac341c9b030cd6f07b1bc7c789fd409993e069d97b58f470014e13e7ad7555659b28ebb86eecff1e571af5cfdccf853d9&ascene=1&uin=NDAyNDg1OTc1&devicetype=Windows+7&version=62060833&lang=zh_CN&pass_ticket=Xac0t7G6Fn6Ff8xKOv%2BZV3MOlalheTY8teSHqzlpyuLWSfzCRGqevP4wG0RM8DRX
 */
public class LRUCache {
    private int capacity;
    private HashMap<String, LRUNode> caches;
    private LRUNode head;
    private LRUNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        //提前准备好，以免经常扩容，但capacity太大的话 也会带来空间损耗
        this.caches = new HashMap<>(capacity);
    }

    /**
     * 如果是不存在的，直接返回null。如果存在，把数据移动到首部头节点，然后再返回旧值。
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        LRUNode lruNode = caches.get(key);
        if (lruNode == null) {
            return null;
        }
        //把访问的节点移动到首部。
        moveNodeToHead(lruNode);
        return caches.get(key).data;
    }

    /**
     * 添加元素的时候首先判断是不是新的元素，如果是新元素，
     * 判断当前的大小是不是大于总容量了，防止超过总链表大小，
     * 如果大于的话直接抛弃最后一个节点，然后再以传入的key\value值创建新的节点。
     * 对于已经存在的元素，直接覆盖旧值，再将该元素移动到头部，然后保存在map中
     * @param key
     * @param data
     */
    public void put(String key, Object data) {
        LRUNode node = caches.get(key);
        if(node==null){
            //不存在则需要放入，且判断容器的大小
            if(caches.size()>=capacity){
                //需要淘汰 尾部
                caches.remove(tail.key);
                //移除尾部
                removeTail();
            }
            node = new LRUNode(key, data);
        }
        //存在则可先把该节点放置头部，再替换里面的值
        node.data = data;
        //把元素移动到首部
        moveNodeToHead(node);
        caches.put(key, node);
    }

    public Object remove(String key) {
        LRUNode node = caches.get(key);
        if (node != null) {
            //当前节点存在上一个节点的指向，说明不是head
            if (node.pre != null) {
                //由于此节点被移走了，那么上一个节点的next原本指向当前节点的，
                //移走之后，需要指向当前节点的next.
                node.pre.next = node.next;
            }
            //当前节点存在下一个节点的指向，说明不是tail
            if (node.next != null) {
                //由于此节点被移走了，那么下个节点的pre原本指向是当前节点的，
                //移走之后，需要指向当前节点的pre.
                node.next.pre = node.pre;
            }

            //当前删除的就是头部节点.那么头部节点需要指向当前节点的next.
            if (node == head) {
                head = node.next;
            }
            //当前删除的就是尾部节点，那么尾部节点就需要指向当前节点的上一个节点。
            if (node == tail) {
                tail = node.pre;
            }
        }
        return caches.remove(key);
    }

    private void removeTail(){
        if(tail==null){
            return;
        }
        //将最后一个节点的上一个节点设置为tail
        tail = tail.pre;
        if(tail==null){
            //都移除没了
            head = null;
        }else{
            // 原本指向的是当前这个的节点的，移除掉了，那么此节点的next也没有了。
            tail.next = null;
        }
    }
    private void moveNodeToHead(LRUNode node) {
        /*
         * 如果当前节点的引用，就是头部节点，无需处理，直接返回。
         */
        if (node == head) {
            return;
        }
        /*
         * 第一个节点的时候，head和tail都是不存在的，直接设置即可。
         * 另外对于节点的node的pre和next也都不存在，处理直接结束.
         */
        if (head == null || tail == null) {
            head = tail = node;
            return;
        }
        //当前节点的next存在的话,很明显是老节点
        if (node.next != null) {
            //此节点要从当前位置直接移动到head,需要对于它的next进行处理。
            //移走之后，next的pre就需要指向，node的pre
            node.next.pre = node.pre;
        }
        //当node的pre存在的时候，不是第一个节点.
        if (node.pre != null) {
            //此节点要从当前位置直接移动到head,需要对于它的pre进行处理。
            //移走之后，pre的next就需要指向 ，node的next
            node.pre.next = node.next;
        }
        //将当前节点移到头
        node.next = head;
        //头部移动到第二个节点，那么head.pre 就是当前节点了。
        head.pre = node;
        // 头部节点，直接指向新的节点。
        head = node;
        //由于是head 那么pre设置为null, next就是上一次head。
        head.pre = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LRUNode node = head;
        sb.append("[");
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.data));
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(5);

        //设置数据
        lru.put("key1","1号技师");
        lru.put("key2","2号技师");
        lru.put("key3","3号技师");
        lru.put("key4","4号技师");
        lru.put("key5","5号技师");
        System.out.println("原始链表："+lru.toString());
        lru.get("key4");
        System.out.println("获取key为4的元素之后的链表:"+lru.toString());

        lru.put("key6","6号技师");
        System.out.println("新添加一个key为6之后的链表:"+lru.toString());

        lru.remove("key3");
        System.out.println("移除key=3的之后的链表:"+lru.toString());
    }
}
