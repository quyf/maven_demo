package cn.quyf.leetcode;

import cn.quyf.leetcode.lru.LRUNode;

/**
 * @author quyf
 * @date 2019/11/8 11:26
 */
public class SingleNodeReverseDemo {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node.next = node2; node2.next = node3; node3.next = node4;

        System.out.println(node);

        Node reverNode = reverseNode(node);
        System.out.println(reverNode);
    }

    private static Node reverseNode(Node node) {
        if(node==null||node.next==null){
            return node;
        }
        //前一个指针节点
        Node pre = null;
        //当前指针节点
        Node cur = node;
        //下一个指针节点
        Node next = null;
        
        while (cur!=null){
            //nextNode 指向下一个节点
            next = cur.next;
            //将当前节点next域指向前一个节点
            cur.next = pre;
            //preNode 指针向后移动
            pre = cur;
            //curNode指针向后移动
            cur = next;
            System.out.println(pre +"-> "+next);
            System.out.println("pre->"+pre+", cur->"+cur);
        }
        return pre;
    }
}

class Node{
    Integer data;
    Node next;

    public Node(Integer data) {
        this.data = data;
    }

    public Node(Integer data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = this;
        sb.append("[");
        while (node != null) {
            sb.append(String.format("%s, ", node.data));
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
