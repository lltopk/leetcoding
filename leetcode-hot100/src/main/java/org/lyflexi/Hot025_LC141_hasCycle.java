package org.lyflexi;

import java.util.*;

/**
 * 141. 环形链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 * 
 * 示例 1：
 * 
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 
 * 示例 3：
 * 
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 * 
 * 提示：
 * 
 * - 链表中节点的数目范围是 [0, 10^4]
 * 
 * - -10^5 <= Node.val <= 10^5
 * 
 * - pos 为 -1 或者链表中的一个 有效索引 。
 * 
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */

/**
 * 答疑
 * 
 * 问：兔子会不会「跳过」乌龟，从来不会和乌龟相遇呢？
 * 
 * 答：这是不可能的。如果有环的话，那么兔子和乌龟都会进入环中。这时用「相对速度」思考，乌龟不动，兔子相对乌龟每次只走一步，这样就可以看出兔子一定会和乌龟相遇了。
 * 
 * 问：为什么代码的 $while$ 循环没有判断 $slow$ 是否为空？
 * 
 * 答：$slow$ 在 $fast$ 后面，如果 $fast$ 不是空，那么 $slow$ 也肯定不是空。好比快人先去探路，慢人走的都是快人走过的路。
 */
public class Hot025_LC141_hasCycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head; // 乌龟和兔子同时从起点出发
        while (fast != null && fast.next != null) {
            slow = slow.next; // 乌龟走一步
            fast = fast.next.next; // 兔子走两步
            if (fast == slow) { // 兔子追上乌龟（套圈），说明有环
                return true;
            }
        }
        return false; // 访问到了链表末尾，无环
    }
}
