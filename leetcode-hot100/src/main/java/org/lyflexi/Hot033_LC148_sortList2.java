package org.lyflexi;

import java.util.*;

/**
 * 148. 排序链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 
 * 示例 1：
 * 
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 
 * 示例 2：
 * 
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 
 * 示例 3：
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 提示：
 * 
 * - 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * 
 * - -10^5 <= Node.val <= 10^5
 * 
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */

/**
 * 方法二：归并排序（迭代）
 * 
 * 方法一的归并是自顶向下计算，需要 $O(log n)$ 的递归栈开销。
 * 
 * 方法二将其改成自底向上计算，空间复杂度优化成 $O(1)$。
 * 
 * 自底向上的意思是：
 * 
 * - 首先，归并长度为 $1$ 的子链表。例如 $[4,2,1,3]$，把第一个节点和第二个节点归并，第三个节点和第四个节点归并，得到 $[2,4,1,3]$。
 * - 然后，归并长度为 $2$ 的子链表。例如 $[2,4,1,3]$，把前两个节点和后两个节点归并，得到 $[1,2,3,4]$。
 * - 然后，归并长度为 $4$ 的子链表。
 * - 依此类推，直到归并的长度大于等于链表长度为止，此时链表已经是有序的了。
 * 
 * 具体算法：
 * 
 * 1. 遍历链表，获取链表长度 $length$。
 * 2. 初始化步长 $step=1$。
 * 3. 循环直到 $step >= length$。
 * 4. 每轮循环，从链表头节点开始。
 * 5. 分割出两段长为 $step$ 的链表，合并，把合并后的链表插到新链表的末尾。重复该步骤，直到链表遍历完毕。
 * 6. 把 $step$ 扩大一倍。回到第 $4$ 步。
 * 
 * 具体细节见代码。
 */
public class Hot033_LC148_sortList2 {
    public ListNode sortList(ListNode head) {
        int length = getListLength(head); // 获取链表长度
        ListNode dummy = new ListNode(0, head); // 用哨兵节点简化代码逻辑
        // step 为步长，即参与合并的链表长度
        for (int step = 1; step < length; step *= 2) {
            ListNode newListTail = dummy; // 新链表的末尾
            ListNode cur = dummy.next; // 每轮循环的起始节点
            while (cur != null) {
                // 从 cur 开始，分割出两段长为 step 的链表，头节点分别为 head1 和 head2
                ListNode head1 = cur;
                ListNode head2 = splitList(head1, step);
                cur = splitList(head2, step); // 下一轮循环的起始节点
                // 合并两段长为 step 的链表
                ListNode[] merged = mergeTwoLists(head1, head2);
                // 合并后的头节点 merged[0]，插到 newListTail 的后面
                newListTail.next = merged[0];
                newListTail = merged[1]; // merged[1] 现在是新链表的末尾
            }
        }
        return dummy.next;
    }

    // 获取链表长度
    private int getListLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // 分割链表
    // 如果链表长度 <= size，不做任何操作，返回空节点
    // 如果链表长度 > size，把链表的前 size 个节点分割出来（断开连接），并返回剩余链表的头节点
    private ListNode splitList(ListNode head, int size) {
        // 先找到 nextHead 的前一个节点
        ListNode cur = head;
        for (int i = 0; i < size - 1 && cur != null; i++) {
            cur = cur.next;
        }

        // 如果链表长度 <= size
        if (cur == null || cur.next == null) {
            return null; // 不做任何操作，返回空节点
        }

        ListNode nextHead = cur.next;
        cur.next = null; // 断开 nextHead 的前一个节点和 nextHead 的连接
        return nextHead;
    }

    // 21. 合并两个有序链表（双指针）
    // 返回合并后的链表的头节点和尾节点
    private ListNode[] mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(); // 用哨兵节点简化代码逻辑
        ListNode cur = dummy; // cur 指向新链表的末尾
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else { // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2; // 拼接剩余链表
        while (cur.next != null) {
            cur = cur.next;
        }
        // 循环结束后，cur 是合并后的链表的尾节点
        return new ListNode[]{dummy.next, cur};
    }
}
