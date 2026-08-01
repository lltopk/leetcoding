package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,728,019/2.4M
 * 通过率
 * 70.8%
 */

/**
 * 队列
 */
public class LC102_levelOrder {
    List<List<Integer>> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            //0. 初始化当前层变量/集合
            List<Integer> bizList = new ArrayList<>();
            int n = deque.size();
            while(n-- > 0){
                //1. 计算当前层
                TreeNode node = deque.poll();
                bizList.add(node.val);
                //2. 计算下一层, 如果是逆序层序遍历, 则下面两个if交换顺序即可
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            ret.add(bizList);
        }
        return ret;
    }
}
