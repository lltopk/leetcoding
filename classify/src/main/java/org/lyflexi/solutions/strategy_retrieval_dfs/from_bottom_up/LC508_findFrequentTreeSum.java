package org.lyflexi.solutions.strategy_retrieval_dfs.from_bottom_up;

import org.lyflexi.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: root = [5,2,-3]
 * 输出: [2,-3,4]
 * 示例 2：
 *
 *
 *
 * 输入: root = [5,2,-5]
 * 输出: [2]
 *
 *
 * 提示:
 *
 * 节点数在 [1, 104] 范围内
 * -105 <= Node.val <= 105
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 59,861/78.2K
 * 通过率
 * 76.6%
 */
public class LC508_findFrequentTreeSum {
    //既然可以返回多个次数相同的和, 那么让和作为key, 次数作为value, 这样恰好key各不相同
    Map<Integer, Integer> map = new HashMap<>();
    int mxCnt = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        int n = 0;
        for(int sum: map.keySet()){
            if(map.get(sum) == mxCnt){
                n++;
            }
        }
        int[] ret = new int[n];
        int i=  0;
        for(int sum: map.keySet()){
            if(map.get(sum) == mxCnt){
                ret[i++] = sum;
            }
        }
        return ret;
    }

    /**
     自底向上
     */
    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int sum = root.val + dfs(root.left) + dfs(root.right);
        map.merge(sum, 1, Integer::sum);
        mxCnt = Math.max(mxCnt, map.get(sum));
        return sum;
    }
}
