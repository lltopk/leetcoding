package org.lyflexi.solutions.strategy_retrieval_bfs;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcoding
 * @Date: 8/1/2026 1:41 PM
 */

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 919. 完全二叉树插入器
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一种算法，将一个新节点插入到一棵完全二叉树中，并在插入后保持其完整。
 *
 * 实现 CBTInserter 类:
 *
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * 输出
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * 解释
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]
 *
 *
 * 提示：
 *
 * 树中节点数量范围为 [1, 1000]
 * 0 <= Node.val <= 5000
 * root 是完全二叉树
 * 0 <= val <= 5000
 * 每个测试用例最多调用 insert 和 get_root 操作 104 次
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 38,487/56.9K
 * 通过率
 * 67.7%
 */
public class LC919_CBTInserter {
    List<TreeNode> list = new ArrayList<>();

    /**
     * 构造二叉树
     * @param root
     */
    public LC919_CBTInserter(TreeNode root) {
        list.add(root);
        int i = 0;
        //最后一个节点一定是node.left == null && node.right == null
        while(i < list.size()) {
            TreeNode node = list.get(i);
            if (node.left != null) list.add(node.left);
            if (node.right != null) list.add(node.right);
            i++;
        }
    }

    int pIdx = 0;
    /**
     找到最后一个存在左子节点， 或者第一个不存在左右子节点的， 即为父节点p
     */
    public int insert(int val) {
        TreeNode node = new TreeNode(val);
        //由于插入一定是优先插入左侧， 因此下面只需判断list.get(pIdx).right != null
        while(list.get(pIdx).right != null) pIdx++;

        TreeNode p = list.get(pIdx);
        if(p.left == null) {
            p.left = node;
        }else{
            p.right = node;
        }
        list.add(node);
        return p.val;
    }

    public TreeNode get_root() {
        return list.get(0);
    }
}
