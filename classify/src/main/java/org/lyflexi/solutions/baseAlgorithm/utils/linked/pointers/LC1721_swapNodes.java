package org.lyflexi.solutions.baseAlgorithm.utils.linked.pointers;

import org.lyflexi.common.ListNode;

/**
 * 1721. 交换链表中的节点
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你链表的头节点 head 和一个整数 k 。
 *
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[1,4,3,2,5]
 * 示例 2：
 *
 * 输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * 输出：[7,9,6,6,8,7,3,0,9,5]
 * 示例 3：
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 示例 4：
 *
 * 输入：head = [1,2], k = 1
 * 输出：[2,1]
 * 示例 5：
 *
 * 输入：head = [1,2,3], k = 2
 * 输出：[1,2,3]
 *
 *
 * 提示：
 *
 * 链表中节点的数目是 n
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 34,174/52.3K
 * 通过率
 * 65.4%
 */
public class LC1721_swapNodes {
    /**
     * 前后指针见LC19. 删除链表的倒数第 N 个结点, 可以一次遍历得到右边的节点
     * 对于本题, 多保存下左边的节点即可
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode left = head, right = head;
        for(int i = 0; i< k -1; i++){
            right = right.next;
        }
        ListNode p = right;

        //固定间距 k - 1
        while(right.next !=null){
            left = left.next;
            right = right.next;
        }
        //交换val
        int temp = left.val;
        left.val = p.val;
        p.val = temp;
        return head;
    }
}
