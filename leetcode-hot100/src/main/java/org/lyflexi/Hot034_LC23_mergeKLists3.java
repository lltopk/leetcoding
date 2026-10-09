package org.lyflexi;

import java.util.*;

/**
 * 23. 合并 K 个升序链表
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 示例 1：
 * 
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 
 * 示例 2：
 * 
 * 输入：lists = []
 * 输出：[]
 * 
 * 示例 3：
 * 
 * 输入：lists = [[]]
 * 输出：[]
 * 
 * 提示：
 * 
 * - k == lists.length
 * 
 * - 0 <= k <= 10^4
 * 
 * - 0 <= lists[i].length <= 500
 * 
 * - -10^4 <= lists[i][j] <= 10^4
 * 
 * - lists[i] 按 升序 排列
 * 
 * - lists[i].length 的总和不超过 10^4
 */

/**
 * 写法二：迭代
 * 
 * 直接自底向上合并链表：
 * 
 * - 两两合并：把 $lists[0]$ 和 $lists[1]$ 合并，合并后的链表保存在 $lists[0]$ 中；把 $lists[2]$ 和 $lists[3]$ 合并，合并后的链表保存在 $lists[2]$ 中；依此类推。
 * - 四四合并：把 $lists[0]$ 和 $lists[2]$ 合并（相当于合并前四条链表），合并后的链表保存在 $lists[0]$ 中；把 $lists[4]$ 和 $lists[6]$ 合并，合并后的链表保存在 $lists[4]$ 中；依此类推。
 * - 八八合并：把 $lists[0]$ 和 $lists[4]$ 合并（相当于合并前八条链表），合并后的链表保存在 $lists[0]$ 中；把 $lists[8]$ 和 $lists[12]$ 合并，合并后的链表保存在 $lists[8]$ 中；依此类推。
 * - 依此类推，直到所有链表都合并到 $lists[0]$ 中。最后返回 $lists[0]$。
 */
public class Hot034_LC23_mergeKLists3 {
    public ListNode mergeKLists(ListNode[] lists) {
        int m = lists.length;
        if (m == 0) {
            return null;
        }
        for (int step = 1; step < m; step *= 2) {
            for (int i = 0; i < m - step; i += step * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + step]);
            }
        }
        return lists[0];
    }

    // 21. 合并两个有序链表
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
        return dummy.next;
    }
}
