package org.lyflexi.solutions.strategy_retrieval_dfs.binary_search_tree;

import org.lyflexi.common.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *
 *
 * 提示：
 *
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,482,757/3.6M
 * 通过率
 * 41.2%
 */
public class LC98_isValidBST {
    long pre = Long.MIN_VALUE;
    /**
     中序遍历
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        //细纠这里的逻辑， 这里! isValidBST(root.left)其实是当前节点（父节点）判断左子树的结果， 由于是中序遍历， 因此左子树一定是提前有结果了
        //假如左子树结果是false， 那么就会进入这个分支， 然后使得当前节点也返回false
        if(! isValidBST(root.left)){
            return false;
        }
        if(root.val <= pre){
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }
}
