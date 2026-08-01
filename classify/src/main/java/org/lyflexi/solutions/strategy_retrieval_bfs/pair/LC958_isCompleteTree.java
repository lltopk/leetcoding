package org.lyflexi.solutions.strategy_retrieval_bfs.pair;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 958. 二叉树的完全性检验
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
 *
 * 在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，节点值为 {1} 和 {2,3} 的两层），且最后一层中的所有节点（{4,5,6}）尽可能靠左。
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的节点不满足条件「节点尽可能靠左」。
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 100] 内
 * 1 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 76,215/135.4K
 * 通过率
 * 56.3%
 */
public class LC958_isCompleteTree {
    record Pair(TreeNode root, int idx){}
    List<Pair> list = new ArrayList<>();
    public boolean isCompleteTree(TreeNode root) {
        list.add(new Pair(root, 0));
        int i = 0;
        while(i < list.size()){
            Pair pair = list.get(i++);
            if(pair.root != null){
                list.add(new Pair(pair.root.left, pair.idx * 2 + 1));
                list.add(new Pair(pair.root.right, pair.idx * 2 + 2));
            }
        }

        //判断节点个数是否等于最后一个元素的二叉索引数， 如果不等于， 说明节点少了
        return list.size() == list.get(list.size() - 1).idx + 1;
    }
}
