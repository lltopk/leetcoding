package org.lyflexi.solutions.strategy_retrieval_bfs;

/**
 * @Description:
 * @Author: lyflexi
 * @project: leetcoding
 * @Date: 8/1/2026 11:46 AM
 */

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2641. 二叉树的堂兄弟节点 II
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
 *
 * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
 *
 * 请你返回修改值之后，树的根 root 。
 *
 * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [5,4,9,1,10,null,7]
 * 输出：[0,0,0,7,7,null,11]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
 * - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
 * 示例 2：
 *
 *
 *
 * 输入：root = [3,1,2]
 * 输出：[0,0,0]
 * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
 * - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
 * - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
 *
 *
 * 提示：
 *
 * 树中节点数目的范围是 [1, 105] 。
 * 1 <= Node.val <= 104
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 23,602/30.7K
 * 通过率
 * 76.8%
 */

/**
 * 两个数组模拟队列： cur nxt
 */
public class LC2641_replaceValueInTree3 {
    //这题其实不是直接求所有的堂兄弟， 而是正反则难
    //每个节点的新值等价于 = 该层节点和 - 自己 - 自己的兄弟节点
    // 化简： 该层节点和 - 父节点的左右子节点和（其中就包括的自己）

    List<TreeNode> cur = new ArrayList<>();
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0 ;
        cur.add(root);
        while(! cur.isEmpty()){
            int nextLayerSum = 0, n = cur.size();
            List<TreeNode> nxt = new ArrayList<>();
            for(TreeNode node: cur) {
                if(node.left != null){
                    nxt.add(node.left);
                    nextLayerSum += node.left.val;
                }
                if(node.right != null){
                    nxt.add(node.right);
                    nextLayerSum += node.right.val;
                }
                if(node.left != null && node.right != null){
                    int s = node.left.val + node.right.val;
                    node.left.val = node.right.val = s;
                }
            }

            //简化第二次for循环， 让上面的for循环先判断当前层的节点的下一层节点是否左右节点都在，如果都在的话先将左右节点都更新为左右节点之和
            //这样第二次遍历的时候， 堂兄弟节点值自然就包括了堂兄弟节点和， 不用再额外计算堂兄弟节点和了
            for(TreeNode node: cur){
                if(node.left != null) node.left.val = nextLayerSum - node.left.val;
                if(node.right != null) node.right.val = nextLayerSum - node.right.val;
            }

            cur = nxt;
        }
        return root;
    }
}
