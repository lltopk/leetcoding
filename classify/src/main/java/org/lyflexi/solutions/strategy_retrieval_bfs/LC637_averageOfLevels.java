package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 * 示例 2:
 *
 *
 *
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 *
 *
 * 提示：
 *
 * 树中节点数量在 [1, 104] 范围内
 * -231 <= Node.val <= 231 - 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 302,418/419.3K
 * 通过率
 * 72.1%
 */
public class LC637_averageOfLevels {
    List<Double> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    public List<Double> averageOfLevels(TreeNode root) {
        if(root == null){
            return List.of();
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            double sum = 0;
            //这里没有while(n-- > 0), 目的是不修改n, 便最后求该层的平均值
            for(int i = 0; i<n; i++){
                TreeNode node = deque.poll();
                sum += node.val;
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(sum / n);
        }
        return ret;
    }
}
