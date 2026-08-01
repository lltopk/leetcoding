package org.lyflexi.solutions.strategy_retrieval_bfs;

import org.lyflexi.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. 二叉树的层序遍历
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 1,728,019/2.4M
 * 通过率
 * 70.8%
 */

/**
 * 两个数组
 */
public class LC102_levelOrder3 {
    List<List<Integer>> ret = new ArrayList<>();
    List<TreeNode> curList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        curList.add(root);
        while(! curList.isEmpty()){
            List<Integer> bizList = new ArrayList<>(curList.size());
            //引用bak指向curList的堆地址
            List<TreeNode> bak = curList;
            //引用curList指向新的空白堆地址
            curList = new ArrayList();
            for(TreeNode node: bak){
                bizList.add(node.val);
                if(node.left != null){
                    curList.add(node.left);
                }
                if(node.right !=null){
                    curList.add(node.right);
                }
            }
            ret.add(bizList);
        }
        return ret;
    }
}
