package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down;

import org.lyflexi.common.TreeNode;

/**
 * 671. 二叉树中第二小的节点
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，即 root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的 第二小的值 。
 *
 * 如果第二小的值不存在的话，输出 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 *
 *
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 231 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 88,337/182.2K
 * 通过率
 * 48.5%
 */
public class LC671_findSecondMinimumValue {
    int ret = -1;
    /**
     隐藏的条件是题目给出的二叉树root一定是最小的
     所以求第二小, 等价于求比root大的最小值
     */
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root, root.val);
        return ret;
    }

    private void dfs(TreeNode root, int min){
        if(root == null){
            return;
        }

        if(root.val > min){
            ret = ret == -1? root.val: Math.min(ret, root.val);
        }
        dfs(root.left, min);
        dfs(root.right, min);
    }

    /**
     ret不能初始化为Integer.MAX_VALUE; 否则当不存在第二小值的时候, 会返回错误的结果
         测试用例
         root =
         [2,2,2]
         输出
         2147483647
         预期结果
         -1
     */
}
