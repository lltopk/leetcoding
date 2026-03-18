package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekCntLongerLegal;

/**
 * 2962. 统计最大元素出现至少 K 次的子数组
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给你一个整数数组 nums 和一个 正整数 k 。
 *
 * 请你统计有多少满足 「 nums 中的 最大 元素」至少出现 k 次的子数组，并返回满足这一条件的子数组的数目。
 *
 * 子数组是数组中的一个连续元素序列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,2,3,3], k = 2
 * 输出：6
 * 解释：包含元素 3 至少 2 次的子数组为：[1,3,2,3]、[1,3,2,3,3]、[3,2,3]、[3,2,3,3]、[2,3,3] 和 [3,3] 。
 * 示例 2：
 *
 * 输入：nums = [1,4,2,1], k = 3
 * 输出：0
 * 解释：没有子数组包含元素 4 至少 3 次。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 106
 * 1 <= k <= 105
 */

/**
 * 不定长滑动窗口求个数, 越长越合法
 */
public class LC2962_countSubarrays {
    public long countSubarrays(int[] nums, int k) {
        //不定长滑动窗口求个数， 越长越合法
        int n = nums.length, l = 0, max = 0;
        long ans = 0;
        for(int i = 0; i< n; i++){
            max = Math.max(max, nums[i]);
        }
        int cnt = 0;//只计算最大值的次数， 用不着字典
        for(int r = 0; r <n; r++){
            cnt+=nums[r] == max?1:0;

            while(cnt>=k){
                cnt-=nums[l] == max?1:0;
                l++;
            }
            ans += l;
        }

        return ans;
    }
}
