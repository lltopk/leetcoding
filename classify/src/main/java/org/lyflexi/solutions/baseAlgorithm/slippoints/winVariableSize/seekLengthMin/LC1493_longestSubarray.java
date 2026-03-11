package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 1493. 删掉一个元素以后全为 1 的最长子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给你一个二进制数组 nums ，你需要从中删掉一个元素。
 *
 * 请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。
 *
 * 如果不存在这样的子数组，请返回 0 。
 *
 *
 *
 * 提示 1：
 *
 * 输入：nums = [1,1,0,1]
 * 输出：3
 * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
 * 示例 2：
 *
 * 输入：nums = [0,1,1,1,0,1,1,0,1]
 * 输出：5
 * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
 * 示例 3：
 *
 * 输入：nums = [1,1,1]
 * 输出：2
 * 解释：你必须要删除一个元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 要么是 0 要么是 1 。
 */

/**
 * 不定尺寸的滑动窗口, 越短越合法
 */
public class LC1493_longestSubarray {
    public int longestSubarray(int[] nums) {
        //不定长滑动窗口求长度, 越短越合法, 条件是窗口内至多有1个0
        int n = nums.length;
        int ans = 0;//求最大
        int l = 0;
        int cnt0 = 0;
        for(int i = 0; i< n ; i++){
            cnt0+=nums[i] == 0? 1:0;
            while(cnt0 > 1){
                cnt0-=nums[l] ==0? 1:0;
                l++;
            }

            ans = Math.max(i - l, ans);//由于不算0, 因此求窗口长度就不用+1了
        }
        return ans;
    }
}
