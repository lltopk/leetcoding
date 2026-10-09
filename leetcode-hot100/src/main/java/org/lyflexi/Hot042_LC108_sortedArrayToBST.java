package org.lyflexi;

import java.util.*;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 * 
 * 示例 1：
 * 
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * 
 * 示例 2：
 * 
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * 
 * 提示：
 * 
 * - 1 <= nums.length <= 10^4
 * 
 * - -10^4 <= nums[i] <= 10^4
 * 
 * - nums 按 严格递增 顺序排列
 */

/**
 * 示例 1 $nums=[-10,-3,0,5,9]$，我们从数组正中间的数 $nums[2]=0$ 开始，把数组一分为二，得到两个小数组：
 * 
 * - 左：$[-10,-3]$。
 * - 右：$[5,9]$。
 * 
 * 答案由三部分组成：
 * 
 * - 根节点：节点值为 $nums[2]=0$。
 * - 把 $nums[2]$ 左边的 $[-10,-3]$ 转换成一棵平衡二叉搜索树，作为答案的左儿子。这是一个和原问题相似的子问题，可以递归解决。
 * - 把 $nums[2]$ 右边的 $[5,9]$ 转换成一棵平衡二叉搜索树，作为答案的右儿子。这是一个和原问题相似的子问题，可以递归解决。
 * 
 * 递归边界：如果数组长度等于 $0$，返回空节点。
 * 
 * 晕递归的同学，可以看视频讲解【基础算法精讲 09】，带你理解递归的本质。
 * 
 * ⚠注意：答案可能不是唯一的。如果 $n$ 是偶数，我们可以取数组正中间左边那个数作为根节点的值，也可以取数组正中间右边那个数作为根节点的值。下面代码取的是正中间右边那个数，即下标为 $\dfrac{n}{2}$ 的数（当 $n$ 是偶数时）。
 */
public class Hot042_LC108_sortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length);
    }

    // 把 nums[left] 到 nums[right-1] 转成平衡二叉搜索树
    private TreeNode dfs(int[] nums, int left, int right) {
        if (left == right) {
            return null;
        }
        int m = (left + right) >>> 1;
        return new TreeNode(nums[m], dfs(nums, left, m), dfs(nums, m + 1, right));
    }
}
