package org.lyflexi.solutions.baseAlgorithm.utils.stack.monotonic_stack.right;

/**
 * 1019. 链表中的下一个更大节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个长度为 n 的链表 head
 *
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 *
 *
 *
 * 输入：head = [2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 *
 *
 * 提示：
 *
 * 链表中节点数为 n
 * 1 <= n <= 104
 * 1 <= Node.val <= 109
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 79,874/118.9K
 * 通过率
 * 67.2%
 */

import org.lyflexi.common.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 单调栈, 从右向左遍历, 计算right[]
 */
public class LC1019_nextLargerNodes {
    /**
     单调栈
     */
    int n = 0;
    public int[] nextLargerNodes(ListNode head) {
        //我们需要反向遍历维护单调栈链表, 只能先将链表反转
        head = reverseList(head);
        //链表反转后, 正向遍历即可了(本质依然是单调栈从右向左遍历)
        int[] ret = new int[n];
        Deque<Integer> sk = new ArrayDeque<>();
        for(ListNode cur = head; cur!=null; cur = cur.next){
            while(! sk.isEmpty() && sk.peek() <= cur.val){
                sk.pop();
            }
            --n;//确保每次循环都要--
            if(! sk.isEmpty()){
                ret[n] = sk.peek();
            }
            sk.push(cur.val);
        }
        return ret;
    }

    // 206. 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
            ++n;
        }
        return pre;
    }
}
