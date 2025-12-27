package org.lyflexi.solutions.Ⅲdivideconquer;

import java.util.Arrays;

/**
 * 2389. 和有限的最长子序列
 * 已解答
 * 简单
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个长度为 n 的整数数组 nums ，和一个长度为 m 的整数数组 queries 。
 *
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是 nums 中 元素之和小于等于 queries[i] 的 子序列 的 最大 长度  。
 *
 * 子序列 是由一个数组删除某些元素（也可以不删除）但不改变剩余元素顺序得到的一个数组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,2,1], queries = [3,10,21]
 * 输出：[2,3,4]
 * 解释：queries 对应的 answer 如下：
 * - 子序列 [2,1] 的和小于或等于 3 。可以证明满足题目要求的子序列的最大长度是 2 ，所以 answer[0] = 2 。
 * - 子序列 [4,5,1] 的和小于或等于 10 。可以证明满足题目要求的子序列的最大长度是 3 ，所以 answer[1] = 3 。
 * - 子序列 [4,5,2,1] 的和小于或等于 21 。可以证明满足题目要求的子序列的最大长度是 4 ，所以 answer[2] = 4 。
 * 示例 2：
 *
 * 输入：nums = [2,3,4,5], queries = [1]
 * 输出：[0]
 * 解释：空子序列是唯一一个满足元素和小于或等于 1 的子序列，所以 answer[0] = 0 。
 */
public class LC2389_answerQueries {
    public int[] answerQueries(int[] nums, int[] queries) {
        int len = queries.length;
        int[] ans = new int[len];
        Arrays.sort(nums);
        for(int i = 1;i<nums.length;i++){
            //原地求前缀和并覆盖原数组
            nums[i] +=nums[i-1];
        }

        for(int i = 0;i<len;i++){
            //如 `...a,x,x,x,b...`, 求>=x+1
            int l = lowerBound(nums,queries[i]+1);
            //前缀和数组下标即所求长度(l-1+1)
            ans[i] = l;
        }

        return ans;
    }

    private int lowerBound(int[] nums, int target){
        int n = nums.length;
        int l = 0, r = n;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]<target){
                l = mid+1;
            }else{
                r = mid;
            }
        }
        return l;
    }
}
