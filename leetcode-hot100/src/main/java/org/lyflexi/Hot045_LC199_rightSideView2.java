package org.lyflexi;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 示例 1：
 * 
 * 输入：root = [1,2,3,null,5,null,4]
 * 
 * 输出：[1,3,4]
 * 
 * 解释：
 * 
 * 示例 2：
 * 
 * 输入：root = [1,2,3,4,null,null,null,5]
 * 
 * 输出：[1,3,4,5]
 * 
 * 解释：
 * 
 * 示例 3：
 * 
 * 输入：root = [1,null,3]
 * 
 * 输出：[1,3]
 * 
 * 示例 4：
 * 
 * 输入：root = []
 * 
 * 输出：[]
 * 
 * 提示:
 * 
 * - 二叉树的节点个数的范围是 [0,100]
 * 
 * - -100 <= Node.val <= 100
 */

/**
 * 方法二：DFS
 * 
 * 视频讲解：【基础算法精讲 10】，欢迎点赞关注~
 * 
 * 先递归右子树，再递归左子树，当某个深度首次到达时，对应的节点就在右视图中。
 */
public class Hot045_LC199_rightSideView2 {
    List<Integer> ret = new ArrayList<>();
    Deque<TreeNode> deque = new ArrayDeque<>();

    public List<Integer> rightSideView(TreeNode root) {
        if(root == null){
            return List.of();
        }
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            int lastV = 0;
            while(n-- > 0){
                TreeNode node = deque.poll();
                lastV = node.val;
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
