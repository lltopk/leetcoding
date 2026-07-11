package org.lyflexi.solutions.baseAlgorithm.utils.linked;

import org.lyflexi.common.ListNode;

/**
 * 725. 分隔链表
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 *
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 *
 * 返回一个由上述 k 部分组成的数组。
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3], k = 5
 * 输出：[[1],[2],[3],[],[]]
 * 解释：
 * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
 * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
 * 解释：
 * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 1000]
 * 0 <= Node.val <= 1000
 * 1 <= k <= 50
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 76,371/123K
 * 通过率
 * 62.1%
 */
public class LC725_splitListToParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while(cur!=null){
            len++;
            cur = cur.next;
        }
        int partition = len / k;
        int remainder = len % k;//平摊给前面的每个部分， 每次减一直到remainder==0
        ListNode[] ret  = new ListNode[k];
        int idx = 0;
        cur = head;
        while(cur!=null){
            ListNode dummy  = new ListNode(0, cur);
            ListNode cur0 = dummy;
            int alloc = partition;
            if(remainder!=0){
                remainder--;
                alloc += 1;
            }
            while(alloc-->0 && cur!=null){
                cur0.next = cur;
                cur0 = cur0.next;
                cur = cur.next;
            }
            //注意这里要及时断开
            cur0.next = null;
            ret[idx++] = dummy.next;
        }
        return ret;
    }
}
