package org.lyflexi.solutions.strategy_retrieval_dfs.from_down_top;

import org.lyflexi.common.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 *
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 * 示例 2：
 *
 *
 * 输入：root = [1,null,2,null,0,3]
 * 输出：3
 *
 *
 * 提示：
 *
 * 树中的节点数在 2 到 5000 之间。
 * 0 <= Node.val <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 68,894/87.8K
 * 通过率
 * 78.5%
 */
public class LC1026_maxAncestorDiff {
    //自底向上求： max(A.val−mn, mx−A.val)， 其中mn mx是上面节点A的最值均大于0, mn <= A.val <= mx
    int ret = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root);
        return ret;
    }
    /**
     自底向上返回最小值，最大值
     */
    private int[] dfs(TreeNode root){
        if(root == null){
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] p = dfs(root.left);
        int[] q = dfs(root.right);
        //开始归给上层节点root
        int mn = Math.min(root.val, Math.min(p[0], q[0]));
        int mx = Math.max(root.val, Math.max(p[1], q[1]));
        ret = Math.max(ret, Math.max(root.val - mn, mx - root.val));
        return new int[]{mn, mx};
    }
}
