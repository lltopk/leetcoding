package org.lyflexi.solutions.iteration;

import org.lyflexi.structDef.ListNode;

/**
 * @Author: ly
 * @Date: 2024/3/23 17:59
 */

/*206. 反转链表
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。

假设链表为 1→2→3→null，我们想要把它改成 null←1←2←3
*/
public class Solution08_ReverseList {
    //头插法
    public ListNode reverseList(ListNode head) {
        //假设链表为 1→2→3→null，我们想要把它改成 3→2→1→null
        ListNode answer = null;
        ListNode curr = head;
        while (curr != null) {
            //暂存next
            ListNode next = curr.next;
            //举例，开始将1指向null
            curr.next = answer;
            answer = curr;
            curr = next;
        }
        return answer;
    }
}
