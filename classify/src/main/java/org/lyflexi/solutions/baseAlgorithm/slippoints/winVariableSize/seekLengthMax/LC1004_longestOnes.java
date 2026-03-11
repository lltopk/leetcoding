package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMax;

/**
 * 1004. 最大连续1的个数 III
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 */

public class LC1004_longestOnes {
    //不定长滑动窗口求最大
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, l = 0;
        int cnt0 = 0;
        for(int r = 0; r<n ; r++){
            cnt0+=nums[r] == 0?1:0;
            while(cnt0 >k){
                cnt0-=nums[l]==0?1:0;
                l++;
            }
            ans = Math.max(ans, r - l +1);
        }

        return ans;
    }
}
