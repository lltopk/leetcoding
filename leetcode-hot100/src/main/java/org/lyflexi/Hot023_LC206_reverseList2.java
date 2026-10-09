package org.lyflexi;

import java.util.*;

/**
 * 206. 反转链表
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 
 * 示例 1：
 * 
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 
 * 示例 2：
 * 
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 
 * 示例 3：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 提示：
 * 
 * - 链表中节点的数目范围是 [0, 5000]
 * 
 * - -5000 <= Node.val <= 5000
 * 
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */

/**
 * 方法二：迭代（头插法）
 * 
 * 视频讲解：【基础算法精讲 06】，制作不易，欢迎点赞~
 * 
 * 简单理解：比如链表为 $1\to 2\to 3$。创建一个新的空链表，然后用头插法依次把节点 $1,2,3$ 插到这个新链表的头部，就得到了链表 $3\to 2\to 1$，这正是反转后的链表。
 * 
 * 头插法的意思是，把一个节点 $node$ 指向链表头节点（$node.next$ 更新为链表头节点），那么 $node$ 就插在了链表的左侧，新链表的头节点为 $node$。
 * 
 * 对于链表 $1\to 2\to 3$，结合代码来说，顺序为：
 * 
 * - 第一轮循环结束后，得到链表 $1$。
 * - 第二轮循环结束后，得到链表 $2\to 1$。
 * - 第三轮循环结束后，得到链表 $3\to 2\to 1$。
 * 注：代码每轮循环结束后，$pre$ 表示最新得到的链表。
 */
public class Hot023_LC206_reverseList2 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre; // 把 cur 插在 pre 链表的前面（头插法）
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
