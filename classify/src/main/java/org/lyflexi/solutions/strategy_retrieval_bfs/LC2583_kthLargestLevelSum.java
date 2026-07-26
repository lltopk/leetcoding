package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.*;

/**
 * 2583. 二叉树中的第 K 大层和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树的根节点 root 和一个正整数 k 。
 *
 * 树中的 层和 是指 同一层 上节点值的总和。
 *
 * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
 *
 * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [5,8,9,2,1,3,7,4,6], k = 2
 * 输出：13
 * 解释：树中每一层的层和分别是：
 * - Level 1: 5
 * - Level 2: 8 + 9 = 17
 * - Level 3: 2 + 1 + 3 + 7 = 13
 * - Level 4: 4 + 6 = 10
 * 第 2 大的层和等于 13 。
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,2,null,3], k = 1
 * 输出：3
 * 解释：最大的层和是 3 。
 *
 *
 * 提示：
 *
 * 树中的节点数为 n
 * 2 <= n <= 105
 * 1 <= Node.val <= 106
 * 1 <= k <= n
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 33,265/61.3K
 * 通过率
 * 54.3%
 */

/**
 * O(N) + 最后排序时间复杂度为Llog(L)
 *
 * 其中N为节点个数, L为层数, 极端情况为单链表, 此时L==N
 */
public class LC2583_kthLargestLevelSum {
    List<Long> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    public long kthLargestLevelSum(TreeNode root, int k) {
        if(root == null){
            return 0l;
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            long sum = 0;
            while(n-- > 0){
                TreeNode node = deque.poll();
                sum += node.val;
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(sum);
        }
        if(ret.size() < k){
            return -1;
        }
        Collections.sort(ret);
        return ret.get(ret.size() - k);
    }
}
