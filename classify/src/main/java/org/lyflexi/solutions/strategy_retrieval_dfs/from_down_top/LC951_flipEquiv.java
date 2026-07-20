package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

import org.lyflexi.common.TreeNode;

/**
 * 951. 翻转等价二叉树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 我们可以为二叉树 T 定义一个 翻转操作 ，如下所示：选择任意节点，然后交换它的左子树和右子树。
 *
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转 等价 于二叉树 Y。
 *
 * 这些树由根节点 root1 和 root2 给出。如果两个二叉树是翻转 等价 的树，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * Flipped Trees Diagram
 * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
 * 输出：true
 * 解释：我们翻转值为 1，3 以及 5 的三个节点。
 * 示例 2:
 *
 * 输入: root1 = [], root2 = []
 * 输出: true
 * 示例 3:
 *
 * 输入: root1 = [], root2 = [1]
 * 输出: false
 *
 *
 * 提示：
 *
 * 每棵树节点数在 [0, 100] 范围内
 * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 30,496/46.1K
 * 通过率
 * 66.2%
 */

/**
 * 自顶向下 有递有归
 */
public class LC951_flipEquiv {
    /**
        这道题不能当作对称二叉树来做， 因为这道题即使不对称， 节点也可以尝试翻转后对称
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        //两个节点任一为null， 则为false
        //只有两个节点都为null， 才为true
        if(root1 == null || root2 == null){
            return root1 == root2;
        }
        if(root1.val != root2.val){
            return false;
        }

        return (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left))
                || (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right));
    }
}
