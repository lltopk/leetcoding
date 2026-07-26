package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 *
 *
 * 示例1：
 *
 *
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 *
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 204,863/307.1K
 * 通过率
 * 66.7%
 */
public class LC515_largestValues {
    List<Integer> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return List.of();
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            int lastV = Integer.MIN_VALUE;
            while(n-- > 0){
                TreeNode node = deque.poll();
                lastV = Math.max(lastV, node.val);
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(lastV);
        }
        return ret;
    }
}
