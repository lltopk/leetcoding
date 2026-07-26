package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 2415. 反转二叉树的奇数层
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵 完美 二叉树的根节点 root ，请你反转这棵树中每个 奇数 层的节点值。
 *
 * 例如，假设第 3 层的节点值是 [2,1,3,4,7,11,29,18] ，那么反转后它应该变成 [18,29,11,7,4,3,1,2] 。
 * 反转后，返回树的根节点。
 *
 * 完美 二叉树需满足：二叉树的所有父节点都有两个子节点，且所有叶子节点都在同一层。
 *
 * 节点的 层数 等于该节点到根节点之间的边数。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,3,5,8,13,21,34]
 * 输出：[2,5,3,8,13,21,34]
 * 解释：
 * 这棵树只有一个奇数层。
 * 在第 1 层的节点分别是 3、5 ，反转后为 5、3 。
 * 示例 2：
 *
 *
 * 输入：root = [7,13,11]
 * 输出：[7,11,13]
 * 解释：
 * 在第 1 层的节点分别是 13、11 ，反转后为 11、13 。
 * 示例 3：
 *
 * 输入：root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * 输出：[0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * 解释：奇数层由非零值组成。
 * 在第 1 层的节点分别是 1、2 ，反转后为 2、1 。
 * 在第 3 层的节点分别是 1、1、1、1、2、2、2、2 ，反转后为 2、2、2、2、1、1、1、1 。
 *
 *
 * 提示：
 *
 * 树中的节点数目在范围 [1, 214] 内
 * 0 <= Node.val <= 105
 * root 是一棵 完美 二叉树
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 36,116/45.1K
 * 通过率
 * 80.0%
 */
public class LC2415_reverseOddLevels2 {
    Deque<TreeNode> deque = new ArrayDeque<>();
    int level = 0;//准备位运算level ^= 1
    public TreeNode reverseOddLevels(TreeNode root) {
        deque.offer(root);
        while(! deque.isEmpty()){
            int n = deque.size();
            List<Integer> layer = new ArrayList<>(n);
            List<TreeNode> layerNodes = new ArrayList<>(n);
            //这里不用while(n-- > 0), 目的是避免改变n
            for(int i = 0; i< n; i++){
                TreeNode node = deque.poll();
                layer.add(node.val);
                layerNodes.add(node);
                if(node.left != null){
                    deque.offer(node.left);
                }
                if(node.right !=null){
                    deque.offer(node.right);
                }
            }
            if(level == 1){
                for(int i = 0 ; i< n; i++){
                    layerNodes.get(i).val = layer.get(n - i - 1);
                }
            }
            level ^= 1;
        }
        return root;
    }
}
