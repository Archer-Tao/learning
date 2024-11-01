package com.tzy.algo;


import com.tzy.entity.ListNode;
import org.apache.commons.lang3.ObjectUtils;

import java.util.PriorityQueue;

/**
 * @description: 链表相关
 * https://labuladong.online/algo/essential-technique/linked-list-skills-summary/
 * <p>
 * 1、合并两个有序链表
 * 2、链表的分解
 * 3、合并 k 个有序链表
 * 4、寻找单链表的倒数第 k 个节点
 * 删除链表的倒数第 N 个结点
 * 5、寻找单链表的中点
 * 6、判断单链表是否包含环并找出环起点
 * 7、判断两个单链表是否相交并找出交点
 * @author: Tao
 * @create: 2024-06-24 10:02
 **/
public class LinkedTest1 {


    /**
     * 合并两个有序列表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode point = head; //指针
        while (l1.next != null && l2.next != null) {
            if (l1.val < l2.val) {
                point.next = l1;
                l1 = l1.next;
            } else {
                point.next = l2;
                l2 = l2.next;
            }
        }
        if (l1.next == null) {
            point.next = l2;
        }
        if (l2.next == null) {
            point.next = l1;
        }
        return head.next;
    }


    /**
     * 分隔链表
     * 思路：我们可以把原链表分成两个小链表，一个链表中的元素大小都小于 x，另一个链表中的元素都大于等于 x，最后再把这两条链表接到一起，就得到了题目想要的结果。
     * <p>
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * https://leetcode.cn/problems/partition-list/
     */
   public ListNode partition(ListNode head, int x) {
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


    /**
     * 链表的分解
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     */
    public ListNode partition2(ListNode head, int x) {
        //设置head 指针
        ListNode cur = head;

        //设置结果链表
        ListNode left = new ListNode(-1), right = new ListNode(-1);
        //创建指针
        ListNode lt = left;
        ListNode gt = right;

        while (ObjectUtils.isNotEmpty(cur)) {
            if (cur.val < x) {
                lt.next = cur;
                lt = lt.next;
            } else {
                gt.next = cur;
                gt = gt.next;
            }
            cur = cur.next;
        }
        lt.next = right;
        return left.next;
    }

    /**
     * 合并 k 个有序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * [[1,4,5],[1,3,4],[2,6]] --> [1,1,2,3,4,4,5,6]
     * 通过优先级队列，最小堆 获取最小值
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        /**
         * 优先级队列 PriorityQueue
         */
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                lists.length, (a, b) -> (a.val - b.val)
        );
        for (ListNode node : lists) {
            pq.add(node);
        }
        ListNode result = new ListNode(-1);
        ListNode p = result;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            p.next = node;
            if (node.next != null) {
                pq.add(node.next); //获得最小值之后，重新插入
            }
            p = p.next;
        }
        return result.next;
    }

    /**
     * 寻找单链表的倒数第 k 个节点
     * 设定两个指针，A 指针前进K之后。继续向前，同时B 指针也开始同步前进。当A到达终点之后，B指针就是倒数第K个节点
     */
    ListNode findFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head;
        int i = 1;

        while (p1 != null) {
            if (i > n) {
                p2 = p2.next;
            }
            p1 = p1.next;
            i++;
        }
        return p2;
    }


    /**
     * 删除链表的倒数第 N 个结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        //设置虚拟头节点
        ListNode p = new ListNode();
        p.next = head;
        ListNode p2 = p;//指针
        int i = 1;
        while (p1.next != null) {
            if (i >= n) {
                p2 = p2.next;
            }
            i++;
            p1 = p1.next;
        }
        p2.next = p2.next.next;
        return p.next;
    }


    /**
     * 链表的中间结点
     * https://leetcode.cn/problems/middle-of-the-linked-list/description/
     * 双指针快慢
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 判断链表是否包含环
     * 快慢节点 保持相对速度为1，这样才能确定不会错过
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * https://leetcode.cn/problems/linked-list-cycle-ii/description/
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }
        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 相交链表
     * https://leetcode.cn/problems/intersection-of-two-linked-lists/
     * <p>
     * 找到相同节点，但是因为长短不一，所以将两个链表合并，在同时进行遍历比较
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {


            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            if (p2 == null) {
                p2=headA;
            }else {
                p2=p2.next;
            }
        }

        return p1;
    }


}
