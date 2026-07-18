package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

/**
 * 111. 二叉树的最小深度
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 908,526/1.6M
 * 通过率
 * 56.9%
 */

import org.lyflexi.common.TreeNode;

/**
 * 自底向上
 */
public class LC111_minDepth {
    /**
     * 问：本题和 104. 二叉树的最大深度 的区别是什么？为什么本题代码要更复杂一些？
     *
     * 答：对于非叶节点，把握一个共同原则：如果一个儿子是空节点，另一个儿子不是空节点，那么答案只能来自非空的那一侧。
     *
     * 求最大深度，空节点返回 0，直接计算 max，一定会取到有节点的那一侧（因为深度比 0 大）。
     * 求最小深度，空节点返回 0，直接计算 min，会取到空节点，不符合「答案只能来自非空的那一侧」。所以求最小深度必须多写一些逻辑。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/minimum-depth-of-binary-tree/solutions/2730984/liang-chong-fang-fa-zi-ding-xiang-xia-zi-0sxz/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        //下面两个if会拦住三种情况, 都是符合预期的
        //1. root.left == null && root.right != null
        //2. root.right == null && root.left != null
        //3. root.left == null && root.right == null
        if(root.left == null){
            return minDepth(root.right) + 1;
        }
        if(root.right == null){
            return minDepth(root.left) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
