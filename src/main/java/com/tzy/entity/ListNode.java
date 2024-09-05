package com.tzy.entity;


/**
 * @description:
 * @author: Tao
 * @create: 2024-08-20 09:54
 **/
public class ListNode  {
  public   int val;
  public ListNode next;

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
