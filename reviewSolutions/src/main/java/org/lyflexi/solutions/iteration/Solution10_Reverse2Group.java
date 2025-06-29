package org.lyflexi.solutions.iteration;
import org.lyflexi.structDef.ListNode;
/**
 * @Author: ly
 * @Date: 2024/3/8 10:10
 */

/*24. 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

实例1：
输入：head = [1,2,3,4]
输出：[2,1,4,3]
*/
public class Solution10_Reverse2Group {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;//不需要创建新链表，在已有链表上就地操作
        ListNode temp = dummyHead;

//      如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。
//      否则，获得 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            //更新节点的指针关系实现两两交换节点。
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            //更新索引
            temp = node1;
        }
        return dummyHead.next;
    }
}
