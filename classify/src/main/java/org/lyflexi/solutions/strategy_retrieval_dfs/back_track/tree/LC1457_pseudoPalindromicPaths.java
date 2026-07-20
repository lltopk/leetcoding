package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.tree;

import org.lyflexi.common.TreeNode;

/**
 * 1457. 二叉树中的伪回文路径
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 *
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [2,3,1,3,1,null,1]
 * 输出：2
 * 解释：上图为给定的二叉树。总共有 3 条从根到叶子的路径：红色路径 [2,3,3] ，绿色路径 [2,1,1] 和路径 [2,3,1] 。
 *      在这些路径中，只有红色和绿色的路径是伪回文路径，因为红色路径 [2,3,3] 存在回文排列 [3,2,3] ，绿色路径 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,1,1,3,null,null,null,null,null,1]
 * 输出：1
 * 解释：上图为给定二叉树。总共有 3 条从根到叶子的路径：绿色路径 [2,1,1] ，路径 [2,1,3,1] 和路径 [2,1] 。
 *      这些路径中只有绿色路径是伪回文路径，因为 [2,1,1] 存在回文排列 [1,2,1] 。
 * 示例 3：
 *
 * 输入：root = [9]
 * 输出：1
 *
 *
 * 提示：
 *
 * 给定二叉树的节点数目在范围 [1, 105] 内
 * 1 <= Node.val <= 9
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 39,269/57.1K
 * 通过率
 * 68.8%
 */
public class LC1457_pseudoPalindromicPaths {
    //对于伪回文序列，按照长度的奇偶性分类讨论：
    //1.对于长为偶数的路径，只要路径上的每个数字的出现次数均为偶数，就一定可以重新排列成回文序列
    //2.对于长为奇数的路径，只要路径上恰好有一个数字的出现次数为奇数（将其拿掉），其余数字的出现次数均为偶数（就变成偶伪回文了）， 就一定可以重新排列成回文序列
    int ret =0;
    public int pseudoPalindromicPaths (TreeNode root) {
        int[] cnt = new int[9];//题目给出的节点值范围是1-9
        dfs(root, cnt);
        return ret;
    }

    private void dfs(TreeNode root, int[] cnt){
        if(root == null){
            return;
        }
        cnt[root.val - 1]++;
        if(root.left == null && root.right ==null && check(cnt)){
            ret++;
        }
        dfs(root.left, cnt);
        dfs(root.right, cnt);
        cnt[root.val - 1]--;
    }

    /**
     * 如果发现字典 cnt 中的 出现次数为奇数 的数字是 0 个或者 1 个，那么说明我们可以得到一个回文序列，返回 1，否则返回 0。
     * @param cnt
     * @return
     */
    private boolean check(int[] cnt){
        int e = 0, o = 0;
        for(int c: cnt){
            if((c & 1) == 0){
                e++;
            }else{
                o++;
            }
        }

        if(o==0 || o==1){
            return true;
        }

        return false;
    }
}
