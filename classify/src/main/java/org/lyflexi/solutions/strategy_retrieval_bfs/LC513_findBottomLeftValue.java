package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 513. 找树左下角的值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 *
 *
 *
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 353,549/478.5K
 * 通过率
 * 73.9%
 */
public class LC513_findBottomLeftValue {
    Deque<TreeNode> deque = new ArrayDeque<>();
    TreeNode ret = new TreeNode();
    /**
     求左下角的值, 一定是逆序层序遍历的最后一个值

     逆序层序遍历: 先加入右节点, 再加入左节点
     */
    public int findBottomLeftValue(TreeNode root) {
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            while(n-- > 0){
                TreeNode node = deque.poll();
                ret = node;
                if(node.right !=null){
                    deque.offer(node.right);
                }
                if(node.left != null){
                    deque.offer(node.left);
                }
            }
        }
        return ret.val;
    }
}
