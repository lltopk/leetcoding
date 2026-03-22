package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntJust;

/**
 * 930. 和相同的二元子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 * 示例 2：
 *
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] 不是 0 就是 1
 * 0 <= goal <= nums.length
 */

/**
 * 恰好型滑动窗口求个数, 两个窗口相减 `shorterLegal(nums, k) - shorterLegal(nums, k-1)`
 */
public class LC930_numSubarraysWithSum {
    public int numSubarraysWithSum(int[] nums, int goal) {
        //不定长恰好型滑动窗口, 要么是越长越合法做差cnt(≥k) - cnt(≥k+1); 要么是越短越合法做差cnt(≤k) - cnt(≤k-1)
        //根据题意, 如果cnt(≥k) - cnt(≥k+1), 当k==0, 会导致l越界
        //因此最终选择cnt(≤k) - cnt(≤k-1)
        int geCnt0 =shorterLegal(nums, goal);
        int geCnt1 =shorterLegal(nums, goal-1);
        return geCnt0 - geCnt1;
    }

    private int shorterLegal(int[] nums, int target){
        if(target < 0){
            return 0;
        }
        int n = nums.length, ans = 0, l = 0, helper = 0;
        for(int r= 0; r<n; r++){
            helper += nums[r];
            //求小于等于target的子数组个数
            while(helper > target){
                helper-=nums[l];
                l++;
            }
            //当target==0, 该行也能计算和为0的子数组个数
            ans+=r - l + 1;
        }
        return ans;
    }
    
}
