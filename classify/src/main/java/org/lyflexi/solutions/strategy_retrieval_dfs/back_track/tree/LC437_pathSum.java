package org.lyflexi.solutions.strategy_retrieval_dfs.back_track.tree;

import org.lyflexi.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 * 面试中遇到过这道题?
 * 1/5
 * 是
 * 否
 * 通过次数
 * 658,540/1.3M
 * 通过率
 * 49.1%
 */

/**
 * 如果二叉树是一条链，本题就和 560. 和为 K 的子数组 完全一样了：统计有多少个非空连续子数组的元素和恰好等于 targetSum。所以你必须先弄明白 560 题（特殊情况），再来做本题（一般情况）。560 题的做法见 我的题解。
 *
 * 作者：灵茶山艾府
 * 链接：https://leetcode.cn/problems/path-sum-iii/solutions/2784856/zuo-fa-he-560-ti-shi-yi-yang-de-pythonja-fmzo/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class LC437_pathSum {
    int ret = 0;
    /**
     枚举右维护左
     前缀和
     dfs
     回溯
     */
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0l, 1);//单独初始化前缀和[0, )本身就是解的情况
        dfs(root, targetSum, cnt, 0l);
        return ret;
    }
    private void dfs(TreeNode root, int targetSum, Map<Long, Integer> cnt, long preS){
        if(root == null){
            return;
        }
        preS += root.val;
        //s2 - s1 = k
        ret += cnt.getOrDefault(preS - targetSum, 0);
        cnt.merge(preS, 1, Integer::sum);
        dfs(root.left, targetSum, cnt, preS);
        dfs(root.right, targetSum, cnt, preS);
        //恢复现场
        cnt.merge(preS, -1, Integer::sum);
    }
}
