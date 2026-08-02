package org.lyflexi.solutions.strategy_retrieval_dfs.from_up_bottom_retV_up;

import org.lyflexi.common.TreeNode;

/**
 * 993. 二叉树的堂兄弟节点
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 * 示例 3：
 *
 *
 *
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 *
 *
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 90,924/151.7K
 * 通过率
 * 59.9%
 */
public class LC993_isCousins {
    int D = 0;//业务属性(比较对象)
    TreeNode P = null;//业务属性(比较对象)
    public boolean isCousins(TreeNode root, int x, int y) {
        return dfs(root, null, 1, x, y);
    }
    /**
         自顶向下, 有递有归
         计算x y是否二叉树的堂兄弟节点(不能是亲兄弟)
     */
    private boolean dfs(TreeNode root, TreeNode p, int depth, int x, int y){
        if(root == null){
            return false;
        }
        //存在x || y
        if(root.val == x || root.val ==y){
            // if(P != null){//之前已经找到x y其中一个
            if(D != 0){//之前已经找到x y其中一个
                return D == depth && P != p;
            }
            D = depth;
            P = p;
        }
        return dfs(root.left, root, depth+1, x, y) || dfs(root.right, root, depth+1, x,y);
    }
}
