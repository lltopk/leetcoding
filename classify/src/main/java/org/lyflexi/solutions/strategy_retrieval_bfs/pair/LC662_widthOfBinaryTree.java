package org.lyflexi.solutions.strategy_retrieval_bfs.pair;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 662. 二叉树最大宽度
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 *
 * 树的 最大宽度 是所有层中最大的 宽度 。
 *
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 *
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 * 示例 2：
 *
 *
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 * 示例 3：
 *
 *
 * 输入：root = [1,3,2,5]
 * 输出：2
 * 解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
 *
 *
 * 提示：
 *
 * 树中节点的数目范围是 [1, 3000]
 * -100 <= Node.val <= 100
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 148,750/324.8K
 * 通过率
 * 45.8%
 */
public class LC662_widthOfBinaryTree {
    int ret = 0;
    Deque<Pair> deque = new ArrayDeque<>();
    //存节点 和 编号, 则子节点编号为2*i+1 2*i+2
    record Pair(TreeNode node, int idx){}
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        deque.offer(new Pair(root, 0));
        while(! deque.isEmpty()){
            int n = deque.size();
            //存子节点编号
            List<Integer> layer = new ArrayList<>();
            for(int i = 0; i<n; i++){
                Pair pair = deque.poll();
                layer.add(pair.idx);
                if(pair.node.left != null){
                    deque.offer(new Pair(pair.node.left, pair.idx*2 + 1));
                }
                if(pair.node.right !=null){
                    deque.offer(new Pair(pair.node.right, pair.idx*2 + 2));
                }
            }
            ret = Math.max(ret, layer.get(layer.size() - 1) - layer.get(0) + 1);

        }
        return ret;
    }
}
