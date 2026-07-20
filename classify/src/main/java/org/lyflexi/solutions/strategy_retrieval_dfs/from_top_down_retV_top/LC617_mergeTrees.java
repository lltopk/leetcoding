package org.lyflexi.solutions.strategy_retrieval_dfs.from_top_down_retV_top;

import org.lyflexi.common.TreeNode;

/**
 * 617. 合并二叉树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你两棵二叉树： root1 和 root2 。
 *
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 *
 * 返回合并后的二叉树。
 *
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 * 示例 2：
 *
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 *
 *
 * 提示：
 *
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -104 <= Node.val <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 604,932/759.4K
 * 通过率
 * 79.7%
 */
public class LC617_mergeTrees {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        //自顶向下DFS， 有递有归
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        //有了上面两个if， 下面两个root都不为null了
        return new TreeNode(root1.val + root2.val,
                mergeTrees(root1.left, root2.left), //合并左子树
                mergeTrees(root1.right, root2.right) //合并右子树
        );
    }
}
