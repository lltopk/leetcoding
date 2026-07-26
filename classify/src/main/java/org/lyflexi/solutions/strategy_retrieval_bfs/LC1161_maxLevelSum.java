package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1161. 最大层内元素和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 *
 * 返回总和 最大 的那一层的层号 x。如果有多层的总和一样大，返回其中 最小 的层号 x。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 * 示例 2：
 *
 * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树中的节点数在 [1, 104]范围内
 * -105 <= Node.val <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 75,727/111.8K
 * 通过率
 * 67.7%
 */
public class LC1161_maxLevelSum {
    int ret = 0, mxSum = Integer.MIN_VALUE, layer = 0;
    Deque<TreeNode> deque = new ArrayDeque<>();
    public int maxLevelSum(TreeNode root) {
        deque.offer(root);
        while(! deque.isEmpty()){
            layer++;
            int n = deque.size();
            int sum = 0;
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
            //注意这里不能用相等, 题目要求如果有多层的总和一样大，返回其中 最小 的层号 x。
            if(sum > mxSum){
                ret = layer;
                mxSum = sum;
            }
        }
        return ret;
    }
}
