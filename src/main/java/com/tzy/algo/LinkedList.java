package com.tzy.algo;



/**
 * @description: 链表相关
 * <p>
 * 1、合并两个有序链表
 * 2、链表的分解
 * 3、合并 k 个有序链表
 * 4、寻找单链表的倒数第 k 个节点
 * 5、寻找单链表的中点
 * 6、判断单链表是否包含环并找出环起点
 * 7、判断两个单链表是否相交并找出交点
 * @author: Tao
 * @create: 2024-06-24 10:02
 **/
public class LinkedList {

    public static void main(String[] args) {
    }

    /**
     * 分隔链表
     * 思路：我们可以把原链表分成两个小链表，一个链表中的元素大小都小于 x，另一个链表中的元素都大于等于 x，最后再把这两条链表接到一起，就得到了题目想要的结果。
     * <p>
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * https://leetcode.cn/problems/partition-list/
     */
    private static ListNode partition(ListNode head, int x) {
        //小于x的链表
        ListNode ltNode = new ListNode(-1);
        //大于x的链表
        ListNode gtNode = new ListNode(-1);

        //创建指针
        ListNode lt = ltNode;
        ListNode gt = gtNode;

        //虚拟头节点
        ListNode p = new ListNode(-1);
        p = head;
        while (p != null) {
            if (p.val < x) {
                lt.next = p;
                lt = lt.next;
            } else {
                gt.next = p;
                gt = gt.next;
            }
            p = p.next;
        }
        lt.next = gtNode;
        return ltNode.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


    }


    /**
     * 合并两个有序列表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        while (l1.next!=null && l2.next!=null){

        }
        return null;
    }

}
