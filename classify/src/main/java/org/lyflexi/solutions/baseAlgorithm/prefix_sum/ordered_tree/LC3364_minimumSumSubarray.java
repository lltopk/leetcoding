package org.lyflexi.solutions.baseAlgorithm.prefix_sum.ordered_tree;

/**
 * 3364. 最小正和子数组
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和 两个 整数 l 和 r。你的任务是找到一个长度在 l 和 r 之间（包含）且和大于 0 的 子数组 的 最小 和。
 *
 * 返回满足条件的子数组的 最小 和。如果不存在这样的子数组，则返回 -1。
 *
 * 子数组 是数组中的一个连续 非空 元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入： nums = [3, -2, 1, 4], l = 2, r = 3
 *
 * 输出： 1
 *
 * 解释：
 *
 * 长度在 l = 2 和 r = 3 之间且和大于 0 的子数组有：
 *
 * [3, -2] 和为 1
 * [1, 4] 和为 5
 * [3, -2, 1] 和为 2
 * [-2, 1, 4] 和为 3
 * 其中，子数组 [3, -2] 的和为 1，是所有正和中最小的。因此，答案为 1。
 *
 * 示例 2：
 *
 * 输入： nums = [-2, 2, -3, 1], l = 2, r = 3
 *
 * 输出： -1
 *
 * 解释：
 *
 * 不存在长度在 l 和 r 之间且和大于 0 的子数组。因此，答案为 -1。
 *
 * 示例 3：
 *
 * 输入： nums = [1, 2, 3, 4], l = 2, r = 4
 *
 * 输出： 3
 *
 * 解释：
 *
 * 子数组 [1, 2] 的长度为 2，和为 3，是所有正和中最小的。因此，答案为 3。
 *
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= l <= r <= nums.length
 * -1000 <= nums[i] <= 1000
 */

import java.util.List;
import java.util.TreeMap;

/**
 * 前缀和与有序集合
 */
public class LC3364_minimumSumSubarray {
    //preS[j+1] - preS[i]要求最大
    //l<= j - i +1 <=r
    //双变量问题转换为枚举右维护左
    //已知preS[j+1], 求最接近preS[j+1]的preS[i],  约束条件是j-r+1<=i<=j-l+1
    //前缀和 + 有序集合红黑树 + 固定大小的滑动窗口[j-r+1, j-l+1]用于维护左, 窗口尺寸为r-l+1
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        Integer[] arr = nums.toArray(Integer[]::new);
        int ans = Integer.MAX_VALUE;
        int n = arr.length;
        int[] preS = new int[n+1];
        //有序集合, key存储左前缀和preS[i]
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for(int rr = 0; rr<n; rr++){
            preS[rr+1] = preS[rr] + arr[rr];

            //窗口右端点形成, 有效的左进入红黑树
            if(rr - l +1 >=0){
                tree.merge(preS[rr-l+1], 1, Integer::sum);
            }
            Integer lower = tree.lowerKey(preS[rr+1]);//最接近preS[j+1]的preS[i]
            if(lower!=null){
                ans = Math.min(ans, preS[rr+1] - lower);
            }
            //窗口左端点形成, 准备剔除窗口
            if(rr - r +1 >=0){
                Integer v = tree.get(preS[rr-r+1]);
                if(v==1){
                    tree.remove(preS[rr-r+1]);
                }else{
                    tree.merge(preS[rr-r+1], -1, Integer::sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE? -1:ans;
    }
}
