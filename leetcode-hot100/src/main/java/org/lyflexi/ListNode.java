package org.lyflexi;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int value) {
        this.val = value;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
