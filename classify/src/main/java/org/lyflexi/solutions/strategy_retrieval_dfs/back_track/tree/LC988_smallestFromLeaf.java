package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.tree;

import org.lyflexi.common.TreeNode;

/**
 * 988. 从叶结点开始的最小字符串
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个 [0, 25] 范围内的值，分别代表字母 'a' 到 'z'。
 *
 * 返回 按字典序最小 的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。
 *
 * 注：字符串中任何较短的前缀在 字典序上 都是 较小 的：
 *
 * 例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。
 * 节点的叶节点是没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [0,1,2,3,4,3,4]
 * 输出："dba"
 * 示例 2：
 *
 *
 *
 * 输入：root = [25,1,3,1,3,0,2]
 * 输出："adz"
 * 示例 3：
 *
 *
 *
 * 输入：root = [2,2,1,null,1,0,null,0]
 * 输出："abc"
 *
 *
 * 提示：
 *
 * 给定树的结点数在 [1, 8500] 范围内
 * 0 <= Node.val <= 25
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 28,838/51.6K
 * 通过率
 * 55.9%
 */
public class LC988_smallestFromLeaf {
    //很大的阿斯克码
    String ret = "~";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ret;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if(root == null){
            return;
        }
        int preLen = path.length();
        path.append((char)('a' + root.val));

        if (root.left == null && root.right == null) {
            path = path.reverse();
            String s = new String(path);
            if(s.compareTo(ret) < 0){
                ret = new String(path);
            }
            path.reverse();
            //这里不能return, 目的是保证最后的回溯， 也能够作用于这里的叶子节点
        }
        dfs(root.left, path);
        dfs(root.right, path);
        //回溯, 要么回溯上面的叶子节点分支， 要么回溯上面的path.append("->");
        path.setLength(preLen);
    }
}
