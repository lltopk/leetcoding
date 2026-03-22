package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntJust;

/**
 * 1248. 统计「优美子数组」
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个整数数组 nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中 「优美子数组」 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 *
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 *
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */

/**
 * 不定长恰好型滑动窗口, 两个滑动窗口相减longerLegal(nums, k) - longerLegal(nums, k+1);
 */
public class LC1248_numberOfSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        //不定长恰好型滑动窗口
        int geCnt0 = longerLegal(nums, k);
        int geCnt1 = longerLegal(nums, k+1);
        return geCnt0 - geCnt1;
    }

    private int longerLegal(int[] nums, int target){
        int n = nums.length;
        int l = 0, helper = 0;//记录奇数的个数
        int ans=  0;
        for(int r = 0; r < n; r++){
            helper+=nums[r]%2==1?1:0;
            while(helper >= target){
                helper-=nums[l]%2==1?1:0;
                l++;
            }
            ans+=l;
        }
        return ans;
    }
}
